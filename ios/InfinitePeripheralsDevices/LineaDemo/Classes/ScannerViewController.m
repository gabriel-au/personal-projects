#import "ScannerViewController.h"
#import "NSDataCrypto.h"
#import "dukpt.h"

//#define LOG_FILE

@implementation ScannerViewController

@synthesize scanTable;

@synthesize lastBarcode;
@synthesize lastBarcodeType;
@synthesize lastCardName;
@synthesize lastCardNumber;
@synthesize lastExpDate;
@synthesize lastCardTrack1;
@synthesize lastCardTrack2;
@synthesize lastCardTrack3;

-(NSString *)getLogFile
{
	NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
	return [[paths objectAtIndex:0] stringByAppendingPathComponent:@"log.txt"];
}

-(void)debug:(NSString *)text
{
	NSDateFormatter *dateFormat=[[NSDateFormatter alloc] init];
	[dateFormat setDateFormat:@"HH:mm:ss:SSS"];
	NSString *timeString = [dateFormat stringFromDate:[NSDate date]];
	
	if([debug length]>10000)
		[debug setString:@""];
	[debug appendFormat:@"%@-%@\n",timeString,text];

	[debugText setText:debug];
#ifdef LOG_FILE
	[debug writeToFile:[self getLogFile]  atomically:YES];
#endif
}

-(void)debugString:(NSString *)text
{
    [self debug:text];
}

-(void)displayAlert:(NSString *)title message:(NSString *)message
{
	UIAlertView *alert = [[UIAlertView alloc] initWithTitle:title message:message delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
	[alert show];
}

-(void)updateBattery
{
    NSError *error=nil;
    
    int percent;
    float voltage;

	if([linea getBatteryCapacity:&percent voltage:&voltage error:&error])
    {
        [voltageLabel setText:[NSString stringWithFormat:@"%d%%,%.1fv",percent,voltage]];
        [battery setHidden:FALSE];
        [voltageLabel setHidden:FALSE];
        if(percent<0.1)
            [battery setImage:[UIImage imageNamed:@"0.png"]];
        else if(percent<40)
            [battery setImage:[UIImage imageNamed:@"25.png"]];
        else if(percent<60)
            [battery setImage:[UIImage imageNamed:@"50.png"]];
        else if(percent<80)
            [battery setImage:[UIImage imageNamed:@"75.png"]];
        else
            [battery setImage:[UIImage imageNamed:@"100.png"]];
    }else
    {
        [battery setHidden:TRUE];
        [voltageLabel setHidden:TRUE];
    }
}

-(IBAction)onPrint:(id)sender
{
    NSError *error;
    
	NSString *selectedPrinterAddress=[[NSUserDefaults standardUserDefaults] objectForKey:@"selectedPrinterAddress"];

	
    if(!selectedPrinterAddress || ![selectedPrinterAddress length])
	{
		UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Bluetooth printing",nil)
														message:NSLocalizedString(@"Please discover and select bluetooth printer from the settings.",nil) delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
		[alert show];
		return;
	}
	
    [progressViewController viewWillAppear:FALSE];
	[self.view addSubview:progressViewController.view];
	[[NSRunLoop currentRunLoop] runUntilDate:[NSDate dateWithTimeIntervalSinceNow:0.01]];
    
	if([linea btSetEnabled:TRUE error:&error])
    {
        if([linea btConnect:selectedPrinterAddress pin:@"0000" error:&error])
        {
            Printer *prn=[Printer sharedDevice];
            prn.delegate=self;
            if([prn connectWithStreams:linea.btInputStream outputStream:linea.btOutputStream error:&error])
            {
                //[prn loadLogo:[UIImage imageNamed:@"Icon-72.png"] align:ALIGN_CENTER error:&error];
                //[prn printLogo:LOGO_NORMAL error:&error];
                
                //CABEÇALHO / LOGO
                [prn printImage:[UIImage imageNamed:@"martins_logo.gif"] align:ALIGN_CENTER error:&error];
                [prn feedPaper:30 error:&error];
                
                NSDate *currDate = [NSDate date];
                NSDateFormatter *dateFormatter = [[NSDateFormatter alloc]init];
                [dateFormatter setDateFormat:@"dd/MM/YY HH:mm:ss"];
                NSString *dateString = [dateFormatter stringFromDate:currDate];
                NSLog(@"%@",dateString);
                
                //CLIENTE/DATA
                [prn printText:@"CLIENTE: ATACADÃO MINEIRO LTDA" error:&error];
                [prn printText:@"CNPJ: 09.876.543/0001-21" error:&error];
                [prn printText:[NSString stringWithFormat:@"DATA/HORA: %@",dateString] error:&error];
                [prn feedPaper:30 error:&error];
                
                //PEDIDO
                if(self.lastBarcode)
                {
                    [prn printText:@"{=C}{+DW}{+DH}{+B}** PEDIDOS **{-B}{-DH}{-DW}" error:&error];
                    [prn feedPaper:10 error:&error];
                    
                    for(int i=0; i<[tableData count]; i++) {
                        //NSLog(@"%@",[[myMutableArrayObject objectAtIndex: i] myMethodDefined]);
                        [prn printText:[NSString stringWithFormat:@"{+B}Cod. Produto:{-B} %@",[tableData objectAtIndex:i]] error:&error];
                    }
                    
                    [prn feedPaper:30 error:&error];
                }
                if(self.lastCardName)
                    [prn printText:[NSString stringWithFormat:@"{+B}Name:{-B} %@",self.lastCardName] error:&error];
                if(self.lastCardNumber)
                    [prn printText:[NSString stringWithFormat:@"{+B}Number:{-B} %@",self.lastCardNumber] error:&error];
                if(self.lastExpDate)
                    [prn printText:[NSString stringWithFormat:@"{+B}Expires:{-B} %@",self.lastExpDate] error:&error];
                
                [prn feedPaper:30 error:&error];
                if(self.lastCardTrack1)
                    [prn printText:[NSString stringWithFormat:@"{+B}Track1:{-B} %@",self.lastCardTrack1] error:&error];
                if(self.lastCardTrack2)
                    [prn printText:[NSString stringWithFormat:@"{+B}Track2:{-B} %@",self.lastCardTrack2] error:&error];
                if(self.lastCardTrack3)
                    [prn printText:[NSString stringWithFormat:@"{+B}Track3:{-B} %@",self.lastCardTrack3] error:&error];
                [prn feedPaper:0 error:&error];
                [prn flushCache:&error];
                [prn waitPrintJob:50 error:&error];
            }else
                ERRMSG(@"Printer connect failed");
        }else
            ERRMSG(@"Bluetooth connect failed");
        [linea btSetEnabled:FALSE error:&error];
    }else
        ERRMSG(@"Bluetooth error");
	[progressViewController.view removeFromSuperview];
}

-(void)cleanPrintInfo
{
    self.lastBarcode=nil;
	self.lastCardName=nil;
	self.lastCardNumber=nil;
	self.lastExpDate=nil;
	self.lastCardTrack1=nil;
	self.lastCardTrack2=nil;
	self.lastCardTrack3=nil;
    
	[printButton setHidden:TRUE];
}

bool scanActive=false;

-(IBAction)scanDown:(id)sender;
{
    NSError *error=nil;
    
	[statusImage setImage:[UIImage imageNamed:@"scanning.png"]];
	[displayText setText:@""];
	[self cleanPrintInfo];
	//refresh the screen
	[[NSRunLoop currentRunLoop] runUntilDate:[NSDate dateWithTimeIntervalSinceNow:0.01]];
    int scanMode;
    
    if([linea getScanMode:&scanMode error:&error] && scanMode==MODE_MOTION_DETECT)
    {
        if(scanActive)
        {
            scanActive=false;
            SHOWERR([linea stopScan:&error]);
        }else {
            scanActive=true;
            SHOWERR([linea startScan:&error]);
        }
    }else
        SHOWERR([linea startScan:&error]);
}

-(IBAction)scanUp:(id)sender;
{
    NSError *error;
    
	[statusImage setImage:[UIImage imageNamed:@"connected.png"]];
    int scanMode;
    
    if([linea getScanMode:&scanMode error:&error] && scanMode!=MODE_MOTION_DETECT)
        SHOWERR([linea stopScan:&error]);
}

-(void)connectionState:(int)state {
	switch (state) {
		case CONN_DISCONNECTED:
		case CONN_CONNECTING:
			//[statusImage setImage:[UIImage imageNamed:@"disconnected.png"]];
			//[displayText setText:@"Scanner not connected"];
			//[battery setHidden:TRUE];
			//[voltageLabel setHidden:TRUE];
			//[scanButton setHidden:TRUE];
			[printButton setHidden:TRUE];
			break;
		case CONN_CONNECTED:
            [debug deleteCharactersInRange:NSMakeRange(0,debug.length)];
            debugText.text=@"";
            scanActive=false;
			//[statusImage setImage:[UIImage imageNamed:@"connected.png"]];
			//[status setString:[NSString stringWithFormat:@"SDK version: %d.%d\n%@ %@ connected\nHardware revision: %@\nFirmware revision: %@\nSerial number: %@",linea.sdkVersion/100,linea.sdkVersion%100,linea.deviceName,linea.deviceModel,linea.hardwareRevision,linea.firmwareRevision,linea.serialNumber]];
			//[displayText setText:status];
			//[scanButton setHidden:FALSE];
            
            [self updateArrayTableView];
            [self updateBattery];
			break;
	}
}

-(void)lineaButtonPressed:(int)which {
    NSLog(@"Pressed button: %d",which);
	[debug setString:@""];
	//[self cleanPrintInfo];
    
	//[displayText setText:@""];
	//[statusImage setImage:[UIImage imageNamed:@"scanning.png"]];
}

-(void)lineaButtonReleased:(int)which {
    NSLog(@"Released button: %d",which);
	//[statusImage setImage:[UIImage imageNamed:@"connected.png"]];
}


-(void)barcodeData:(NSString *)barcode isotype:(NSString *)isotype
{
	[self cleanPrintInfo];
	
	self.lastBarcode=barcode;
	self.lastBarcodeType=isotype;
	
	[status setString:@""];
	[status appendFormat:@"ISO Type: %@\n",isotype];
	[status appendFormat:@"Barcode: %@",barcode];
    
	[displayText setText:status];
    
    [tableData addObject:barcode];
    [scanTable reloadData];
    
    NSLog(@"1: %@",barcode);
    NSLog(@"Count: %d", [tableData count]);

	[self updateBattery];
	if([linea.deviceModel rangeOfString:@"BL"].location==NSNotFound)
	{
		[printButton setHidden:TRUE];
	}else {
		[printButton setHidden:FALSE];
	}
}

-(void)barcodeData:(NSString *)barcode type:(int)type {
	[self cleanPrintInfo];
    
	self.lastBarcode=barcode;
	self.lastBarcodeType=[linea barcodeType2Text:type];
	
	[status setString:@""];

	[status appendFormat:@"Type: %d\n",type];
	[status appendFormat:@"Type text: %@\n",[linea barcodeType2Text:type]];
	[status appendFormat:@"Barcode: %@",barcode];
	[displayText setText:status];
    
    [tableData addObject:barcode];
    [scanTable reloadData];
    
    //NSLog(@"2: %@",barcode);
    //NSLog(@"Count: %d", [tableData count]);

	[self updateBattery];
	if([linea.deviceModel rangeOfString:@"BL"].location==NSNotFound)
	{
		[printButton setHidden:TRUE];
	}else {
		[printButton setHidden:FALSE];
	}
}

-(void)magneticCardData:(NSString *)track1 track2:(NSString *)track2 track3:(NSString *)track3 {
	[self cleanPrintInfo];
	
	self.lastCardTrack1=track1;
	self.lastCardTrack2=track2;
	self.lastCardTrack3=track3;
	
	[status setString:@""];
	
	NSDictionary *card=[linea msProcessFinancialCard:track1 track2:track2];
	if(card)
	{
		self.lastCardName=[card valueForKey:@"cardholderName"];
		self.lastCardNumber=[card valueForKey:@"accountNumber"];
		self.lastExpDate=[NSString stringWithFormat:@"%@/%@\n",[card valueForKey:@"expirationMonth"],[card valueForKey:@"expirationYear"]];
        
        //[tableData addObject:[card valueForKey:@"accountNumber"]];
        //[scanTable reloadData];
		
        //NSLog(@"Card: @%", [card valueForKey:@"accountNumber"]);
        
		
		if(self.lastCardName)
			[status appendFormat:@"Name: %@\n",self.lastCardName];
		if(self.lastCardNumber)
			[status appendFormat:@"Number: %@\n",self.lastCardNumber];
		if(self.lastExpDate)
			[status appendFormat:@"Expiration: %@\n",self.lastExpDate];
		[status appendString:@"\n"];
	}
    
    NSLog(@"Card: %@",self.lastCardNumber);
    NSLog(@"Card: %@",self.lastCardTrack1);
    NSLog(@"Card: %@",self.lastCardTrack2);
    NSLog(@"Card: %@",self.lastCardTrack3);
	
	if(track1!=NULL)
		[status appendFormat:@"Track1: %@\n",track1];
	if(track2!=NULL)
		[status appendFormat:@"Track2: %@\n",track2];
	if(track3!=NULL)
		[status appendFormat:@"Track3: %@\n",track3];
	[displayText setText:status];
	
	int sound[]={2730,150,0,30,2730,150};
	[linea playSound:100 beepData:sound length:sizeof(sound) error:nil];
	[self updateBattery];
	if([linea.deviceModel rangeOfString:@"BL"].location==NSNotFound)
	{
		[printButton setHidden:TRUE];
	}else
    {
		[printButton setHidden:FALSE];
	}
    
    [self displayAlert:@"CARTÃO DE CRÉDITO" message:[NSString stringWithFormat:@"%@",self.lastCardTrack2]];
}

-(NSString *)toHexString:(void *)data length:(int)length space:(bool)space
{
	const char HEX[]="0123456789ABCDEF";
	char s[2000];
	
	int len=0;
	for(int i=0;i<length;i++)
	{
		s[len++]=HEX[((uint8_t *)data)[i]>>4];
		s[len++]=HEX[((uint8_t *)data)[i]&0x0f];
        if(space)
            s[len++]=' ';
	}
	s[len]=0;
	return [NSString stringWithCString:s encoding:NSASCIIStringEncoding];
}

-(void)magneticCardRawData:(NSData *)tracks {
    //NSLog(@"raw data: %@",[self toHexString:(void *)[tracks bytes] length:[tracks length]]);
	[status setString:[self toHexString:(void *)[tracks bytes] length:[tracks length] space:true]];
	[displayText setText:status];
	
	int sound[]={2700,150,5400,150};
	[linea playSound:100 beepData:sound length:sizeof(sound) error:nil];
	[self updateBattery];
}

-(uint16_t)crc16:(uint8_t *)data  length:(int)length crc16:(uint16_t)crc16
{
	if(length==0) return 0;
	int i=0;
	while(length--)
	{
		crc16=(uint8_t)(crc16>>8)|(crc16<<8);
		crc16^=*data++;
		crc16^=(uint8_t)(crc16&0xff)>>4;
		crc16^=(crc16<<8)<<4;
		crc16^=((crc16&0xff)<<4)<<1;
		i++;
	}
	return crc16;
}

-(void)magneticJISCardData:(NSString *)data {
    [displayText setText:[NSString stringWithFormat:@"JIS card data:\n%@",data]];

	int sound[]={2730,150,0,30,2730,150};
	[linea playSound:100 beepData:sound length:sizeof(sound) error:nil];
	[self updateBattery];
}

//demo by sending encrypted data to tgate servers for processing
#define POST_TGATE
//demo by sending encrypted data to element express servers for processing
//element express code is incomplte
//#define POST_ELEMENTEXPRESS

#ifdef POST_TGATE
-(void)tgatePost:(NSString *)function data:(NSString *)data
{
    NSURL *url=[NSURL URLWithString:[NSString stringWithFormat:@"https://gatewaystage.itstgate.com/SmartPayments/transact3.asmx/%@",function]];
    NSData *postData=[data dataUsingEncoding:NSASCIIStringEncoding];
    
    NSString *postLength = [NSString stringWithFormat:@"%d", [postData length]];
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    [request setURL:url];
    [request setHTTPMethod:@"POST"];
    [request setValue:postLength forHTTPHeaderField:@"Content-Length"];
    [request setValue:@"application/x-www-form-urlencoded" forHTTPHeaderField:@"Content-Type"];
    [request setHTTPBody:postData];
    
    [progressViewController viewWillAppear:FALSE];
	[self.view addSubview:progressViewController.view];
	[[NSRunLoop currentRunLoop] runUntilDate:[NSDate dateWithTimeIntervalSinceNow:0.01]];
	
    NSError *error;
    NSURLResponse *response;
    NSData *urlData=[NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
	[progressViewController.view removeFromSuperview];
    if(urlData)
    {
        NSLog(@"HTTPS POST completed: %@",[[NSString alloc] initWithData:urlData encoding:NSASCIIStringEncoding]);
        [self displayAlert:@"TGate response" message:[[NSString alloc] initWithData:urlData encoding:NSASCIIStringEncoding]];
    }else {
        NSLog(@"HTTPS POST failed with error: %@",[error localizedDescription]);
        [self displayAlert:@"Error" message:[NSString stringWithFormat:@"TGate connection failed with error: %@",error.localizedDescription]];
    }
}
#endif
#ifdef POST_ELEMENTEXPRESS
-(void)eePost:(NSString *)data
{
    NSURL *url=[NSURL URLWithString:@"https://certtransaction.elementexpress.com/"];
    NSData *postData=[data dataUsingEncoding:NSASCIIStringEncoding];
    
    NSString *postLength = [NSString stringWithFormat:@"%d", [postData length]];
    
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    [request setURL:url];
    [request setHTTPMethod:@"POST"];
    [request setValue:postLength forHTTPHeaderField:@"Content-Length"];
    [request setValue:@"text/xml" forHTTPHeaderField:@"Content-Type"];
    [request setHTTPBody:postData];
    
    [progressViewController viewWillAppear:FALSE];
	[self.view addSubview:progressViewController.view];
	[[NSRunLoop currentRunLoop] runUntilDate:[NSDate dateWithTimeIntervalSinceNow:0.01]];
	
    NSError *error;
    NSURLResponse *response;
    NSData *urlData=[NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
	[progressViewController.view removeFromSuperview];
    if(urlData)
    {
        NSLog(@"HTTPS POST completed: %@",[[NSString alloc] initWithData:urlData encoding:NSASCIIStringEncoding]);
        [self displayAlert:@"TGate response" message:[[NSString alloc] initWithData:urlData encoding:NSASCIIStringEncoding]];
    }else {
        NSLog(@"HTTPS POST failed with error: %@",[error localizedDescription]);
        [self displayAlert:@"Error" message:[NSString stringWithFormat:@"TGate connection failed with error: %@",error.localizedDescription]];
    }
}
#endif

-(void)magneticCardEncryptedData:(int)encryption data:(NSData *)data {
    [self magneticCardEncryptedData:encryption tracks:0 data:data];
}

//the new notification, sent by 1.29+ sdk
-(void)magneticCardEncryptedData:(int)encryption tracks:(int)tracks data:(NSData *)data {

	[status setString:@""];
    NSLog(@"Encrypted card data, tracks: %d, encryption: %d",tracks,encryption);
    
    if(tracks!=0)
    {
        //you can check here which tracks are read and discard the data if the requred ones are missing
        // for example:
        //if(!(tracks&2)) return; //bail out if track 2 is not read
    }
	
	//last used decryption key is stored in preferences
	NSString *decryptionKey=[[NSUserDefaults standardUserDefaults] objectForKey:@"DecryptionKey"];
	if(decryptionKey==nil || decryptionKey.length!=32)
		decryptionKey=@"11111111111111111111111111111111"; //sample default
    
    if(encryption==ALG_AES256 || encryption==ALG_EH_AES256)
    {
        NSData *decrypted=[data AESDecryptWithKey:[decryptionKey dataUsingEncoding:NSASCIIStringEncoding]];
        //basic check if the decrypted data is valid
        if(decrypted)
        {
            uint8_t *bytes=(uint8_t *)[decrypted bytes];
            for(int i=0;i<([decrypted length]-2);i++)
            {
                if(i>(4+16) && !bytes[i])
                {
                    uint16_t crc16=[self crc16:bytes length:(i+1) crc16:0];
                    uint16_t crc16Data=(bytes[i+1]<<8)|bytes[i+2];
                    
                    if(crc16==crc16Data)
                    {
                        int snLen=0;
                        for(snLen=0;snLen<16;snLen++)
                            if(!bytes[4+snLen])
                                break;
                        NSString *sn=[[NSString alloc] initWithBytes:&bytes[4] length:snLen encoding:NSASCIIStringEncoding];
                        //do something with that serial number
                        NSLog(@"Serial number in encrypted packet: %@",sn);
                        
                        //crc matches, extract the tracks then
                        int dataLen=i;
                        //check for JIS card
                        if(bytes[4+16]==0xF5)
                        {
                            NSString *data=[[NSString alloc] initWithBytes:&bytes[4+16+1] length:(dataLen-4-16-2) encoding:NSASCIIStringEncoding];
                            //pass to the non-encrypted function to display JIS card
                            [self magneticJISCardData:data];
                        }else
                        {
                            int t1=-1,t2=-1,t3=-1,tend;
                            NSString *track1=nil,*track2=nil,*track3=nil;
                            //find the tracks offset
                            for(int j=(4+16);j<dataLen;j++)
                            {
                                if(bytes[j]==0xF1)
                                    t1=j;
                                if(bytes[j]==0xF2)
                                    t2=j;
                                if(bytes[j]==0xF3)
                                    t3=j;
                            }
                            if(t1!=-1)
                            {
                                if(t2!=-1)
                                    tend=t2;
                                else
                                    if(t3!=-1)
                                        tend=t3;
                                    else
                                        tend=dataLen;
                                track1=[[NSString alloc] initWithBytes:&bytes[t1+1] length:(tend-t1-1) encoding:NSASCIIStringEncoding];
                            }
                            if(t2!=-1)
                            {
                                if(t3!=-1)
                                    tend=t3;
                                else
                                    tend=dataLen;
                                track2=[[NSString alloc] initWithBytes:&bytes[t2+1] length:(tend-t2-1) encoding:NSASCIIStringEncoding];
                            }
                            if(t3!=-1)
                            {
                                tend=dataLen;
                                track3=[[NSString alloc] initWithBytes:&bytes[t3+1] length:(tend-t3-1) encoding:NSASCIIStringEncoding];
                            }
                            
                            //pass to the non-encrypted function to display tracks
                            [self magneticCardData:track1 track2:track2 track3:track3];
                        }
                        return;
                    }
                }
            }
        }
        [status setString:NSLocalizedString(@"Card data cannot be decrypted, possibly key is invalid",nil)];
    }
    if(encryption==ALG_EH_IDTECH)
    {//IDTECH
        //find the tracks, turn to ascii hex the data
        int index=0;
        uint8_t *bytes=(uint8_t *)[data bytes];
        
        index++; //card encoding type
        index++; //track status
        int t1Len=bytes[index++]; //track 1 unencrypted length
        int t2Len=bytes[index++]; //track 2 unencrypted length
        int t3Len=bytes[index++]; //track 3 unencrypted length
        NSString *t1masked=[[NSString alloc] initWithBytes:&bytes[index] length:t1Len encoding:NSASCIIStringEncoding];
        index+=t1Len; //track 1 masked
        NSString *t2masked=[[NSString alloc] initWithBytes:&bytes[index] length:t2Len encoding:NSASCIIStringEncoding];
        index+=t2Len; //track 2 masked
        NSString *t3masked=[[NSString alloc] initWithBytes:&bytes[index] length:t3Len encoding:NSASCIIStringEncoding];
        index+=t3Len; //track 3 masked
        uint8_t *encrypted=&bytes[index]; //encrypted
        int encLen=[data length]-index-10-40;
        index+=encLen;
        index+=20; //track1 sha1
        index+=20; //track2 sha1
        uint8_t *dukptser=&bytes[index]; //dukpt serial number
        
        [status appendFormat:@"IDTECH card format\n"];
        [status appendFormat:@"Track1: %@\n",t1masked];
        [status appendFormat:@"Track2: %@\n",t2masked];
        [status appendFormat:@"Track3: %@\n",t3masked];
        [status appendFormat:@"\r\nEncrypted: %@\n",[self toHexString:encrypted length:encLen space:true]];
        [status appendFormat:@"KSN: %@\n\n",[self toHexString:dukptser length:10 space:true]];
        
        //try decrypting the data
        //calculate the IPEK based on the BDK and serial number
        //insert your own BDK here and calculate the IPEK, for the demo we are using predefined IPEK, that is loaded on the test units
        //uint8_t bdk[16]={...};
        uint8_t ipek[16]={0x82,0xDF,0x8A,0xC0,0x22,0x91,0x62,0xAF,0x04,0x0C,0xF4,0xD0,0x76,0x43,0x72,0x79};
        //dukptDeriveIPEK(bdk,dukptser,ipek);
        
        //calculate the key based on the serial number and IPEK
        uint8_t dukptKey[16]={0};
        dukptCalculateDataKey(dukptser,ipek,dukptKey);
        
        //decrypt the data with the calculated key
        uint8_t decrypted[512];
        trides_crypto(kCCDecrypt,0,encrypted,encLen,decrypted,dukptKey);
        NSString *t1=@"";
        NSString *t2=@"";
        if(t1Len)
            t1=[[NSString alloc] initWithBytes:&decrypted[0] length:t1Len encoding:NSASCIIStringEncoding];
        if(t2Len)
            t2=[[NSString alloc] initWithBytes:&decrypted[t1Len] length:t2Len encoding:NSASCIIStringEncoding];
        if([t1 hasPrefix:@"%B"])
            [status appendFormat:@"Decrypted T1: %@\n",t1];
        else
            [status appendFormat:@"Decrypting T1 failed"];
        if([t2 hasPrefix:@";"])
            [status appendFormat:@"Decrypted T2: %@\n",t2];
        else
            [status appendFormat:@"Decrypting T2 failed"];
        
        if(t1masked.length>0 && [linea msProcessFinancialCard:t1masked track2:t2masked])
        {//if the card is a financial card, try sending to a processor for verification
            NSLog(@"%@",[linea msProcessFinancialCard:t1masked track2:t2masked]);
#ifdef POST_TGATE            
#define TGATE_USER @""
#define TGATE_PASS @""
            NSString *extData=[NSString stringWithFormat:@"<Track1>%@</Track1><SecureFormat>SecureMag</SecureFormat><SecurityInfo>%@</SecurityInfo>",
                               [self toHexString:encrypted length:encLen space:false],
                               [self toHexString:dukptser length:10 space:false]];
            //[self tgatePost:@"GenerateCardToken" data:[NSString stringWithFormat:@"UserName=%@&Password=%@&CardNumber=&ExtData=%@",TGATE_USER,TGATE_PASS,extData]];
            [self tgatePost:@"ProcessCreditCard" data:[NSString stringWithFormat:@"UserName=%@&Password=%@&TransType=Auth&CardNum=&ExpDate=&MagData=&NameOnCard=&Amount=0.01&InvNum=&PNRef=&Zip=&Street=&CVNum=&ExtData=%@",TGATE_USER,TGATE_PASS,extData]];
#endif
#ifdef POST_ELEMENTEXPRESS
            NSMutableString *s=[[NSMutableString alloc] init];
            [s appendFormat:@"<HealthCheck xmlns='https://transaction.elementexpress.com'>"];
            [s appendFormat:@"<Credentials>"];
            [s appendFormat:@"<AccountID>%d</AccountID>",1009617];
            [s appendFormat:@"<AccountToken>%@</AccountToken>",@"782C61317113B722B9AEEE0DA3C1450B6AF6021C12A48699710F115E3AE5D7D9D4B3ED01"];
            [s appendFormat:@"<AcceptorID>%d</AcceptorID>",3928907];
            [s appendFormat:@"</Credentials>"];
            [s appendFormat:@"<Application>"];
            [s appendFormat:@"<ApplicationID>%d</ApplicationID>",1360];
            [s appendFormat:@"<ApplicationName>HealthCheck</ApplicationName>"];
            [s appendFormat:@"<ApplicationVersion>1.0</ApplicationVersion>"];
            [s appendFormat:@"</Application>"];
            [s appendFormat:@"</HealthCheck>"];
            NSLog(@"to send: %@",s);
            [self eePost:s];
#endif            
        }
 
    }
	[displayText setText:status];
}

-(void)rfCardDetected:(int)cardIndex type:(int)type uuid:(NSData *)uuid
{
	[status setString:@""];
    
    [status appendFormat:@"RF Card: %d\nType: %d\nUUID: %@",cardIndex,type,uuid];
	[displayText setText:status];
    
	int sound[]={2730,150,0,30,2730,150};
	[linea playSound:100 beepData:sound length:sizeof(sound) error:nil];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [tableData count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *simpleTableIdentifier = @"SimpleTableItem";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:simpleTableIdentifier];
    
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:simpleTableIdentifier];
    }
    
    //NSLog(@"\nTableView");
    //NSLog(@"\nTeste: %@", tableData);
    
    cell.textLabel.text = [tableData objectAtIndex:(indexPath.row)];
    return cell;
}

- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return YES if you want the specified item to be editable.
    return YES;
}

// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        //add code here for when you hit delete
        [tableData removeObjectAtIndex:(indexPath.row)];
        [scanTable reloadData];
    }
}

- (void)viewWillAppear:(BOOL)animated
{
    //update display according to current linea state
    [self updateArrayTableView];
	[self connectionState:linea.connstate];
}

- (void)viewDidLoad
{
    scannerViewController=self;
	status=[[NSMutableString alloc] init];
	debug=[[NSMutableString alloc] init];
#ifdef LOG_FILE
	NSFileManager *fileManger = [NSFileManager defaultManager];
	if ([fileManger fileExistsAtPath:[self getLogFile]])
	{
		[debug appendString:[[NSString alloc] initWithContentsOfFile:[self getLogFile]]];
		[debugText setText:debug];
	}
#endif
	debugText.font=[debugText.font fontWithSize:8];
	linea=[Linea sharedDevice];
	[linea addDelegate:self];
    
    tableData = [[NSMutableArray alloc] init];
    //[tableData addObject:@"Teste"];
    
    //NSLog(@"Teste: %@", tableData);
    
    [scanTable reloadData];
    
    [super viewDidLoad];
}
-(void) updateArrayTableView {
    //NSLog(@"\nupdateArray");
    //NSLog(@"\nTeste: %@", tableData);
    
    if([tableData count] > 0) {
        [printButton setHidden:FALSE];
        [scanTable reloadData];
    }

}


@end
