#import "PrinterSDKTestViewController.h"
#import "NSDataCrypto.h"

@implementation PrinterSDKTestViewController

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
	if([debug length]>5000)
		[debug setString:@""];
	[debug appendFormat:@"%@-%@\n",timeString,text];

	[debugText setText:debug];
#ifdef LOG_FILE
	[debug writeToFile:[self getLogFile]  atomically:YES encoding:NSASCIIStringEncoding error:nil];
#endif
}

-(void)displayAlert:(NSString *)title message:(NSString *)message
{
	UIAlertView *alert = [[UIAlertView alloc] initWithTitle:title message:message delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
	[alert show];
}

static uint16_t crc16ccit(uint8_t *buf, uint32_t n, uint16_t crc)
{
    if(n==0) return 0;
    while(n--)
    {
		crc=(uint8_t)(crc>>8)|(crc<<8);
		crc^=*buf++;
		crc^=(uint8_t)(crc&0xff)>>4;
		crc^=(crc<<8)<<4;
		crc^=((crc&0xff)<<4)<<1;
    }
    return crc;
}

#define COMMAND(operation,x) if(!x){[self displayAlert:@"Error" message:[NSString stringWithFormat:@"%@ failed with error: %@",operation,err.localizedDescription]]; return; }

-(void)updateBattery
{
    NSError *err;
    int capacity=0;
    COMMAND(@"Reading battery",[prn getInfo:INFO_BATPERCENT data:&capacity error:&err]);
    if(capacity!=-1)
    {
        if(capacity<15)
            [battery setImage:[UIImage imageNamed:@"0.png"]];
        else
            if(capacity<30)
                [battery setImage:[UIImage imageNamed:@"25.png"]];
            else
                if(capacity<65)
                    [battery setImage:[UIImage imageNamed:@"50.png"]];
                else
                    if(capacity<90)
                        [battery setImage:[UIImage imageNamed:@"75.png"]];
                    else
                        [battery setImage:[UIImage imageNamed:@"100.png"]];
    }
    [battery setHidden:FALSE];
}

-(void)prnConnectionState:(int)state
{
	switch (state) {
		case CONN_DISCONNECTED:
		case CONN_CONNECTING:
			[statusImage setImage:[UIImage imageNamed:@"disabled.png"]];
			[displayText setText:@"Printer not connected"];
			[battery setHidden:TRUE];
            [paperStatusLabel setText:@""];
			break;
		case CONN_CONNECTED:
            [statusImage setImage:[UIImage imageNamed:@"normal.png"]];
			[displayText setText:[NSString stringWithFormat:@"%@ %@ connected\nHardware revision: %@\nFirmware revision: %@\nSerial number: %@",prn.deviceName,prn.deviceModel,prn.hardwareRevision,prn.firmwareRevision,prn.serialNumber]];
            [paperStatusLabel setText:@""];
            [self updateBattery];
			break;
	}
}

-(void)paperStatus:(BOOL)present
{
    [paperStatusLabel setText:present?@"":@"Printer is out of paper"];
}

-(IBAction)onFontsDemo:(id)sender;
{
    NSError *err;
    
	COMMAND(@"Print text",[prn printText:@"{=C}FONT SIZES" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
    COMMAND(@"Print text",[prn printText:@"{=F0}Font 9x16\n{+DW}Double width\n{-DW}{+DH}Double height\n{+DW}{+DH}DW & DH" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print text",[prn printText:@"{=F1}Font 12x24\n{+DW}Double width\n{-DW}{+DH}Double height\n{+DW}{+DH}DW & DH" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	
	COMMAND(@"Print text",[prn printText:@"{=C}FONT STYLES\n{=L}Normal\n{+B}Bold\n{+I}Bold Italic{-I}{-B}\n{+U}Underlined{-U}\n{+V}Inversed{-V}\n" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print text",[prn printText:@"{=C}FONT ROTATION\n{=L}{=R1}Rotated 90 degrees\n{=R2}Rotated 180 degrees\n" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	
	COMMAND(@"Print text",[prn printText:@"{+W}{=F0}This function demonstrates the use of the built-in word-wrapping capability" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print text",[prn printText:@"{+W}{=F1}This function demonstrates the use of the built-in word-wrapping capability" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print text",[prn printText:@"{+W}{=F0}{=J}This function demonstrates the use of the built-in word-wrapping capability and the use of justify" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print text",[prn printText:@"{+W}{=F1}{=J}This function demonstrates the use of the built-in word-wrapping capability and the use of justify" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);

	COMMAND(@"Print text",[prn printText:@"{+W}{=L}Left {=R}and right aligned" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
    
	COMMAND(@"Feed paper",[prn feedPaper:0 error:&err]);
    COMMAND(@"Wait for job",[prn waitPrintJob:30 error:&err]);
    
    [self updateBattery];
}

- (IBAction)onSelfTest:(id)sender;
{
    NSError *err;
    
	COMMAND(@"Print logo",[prn printLogo:LOGO_NORMAL error:&err]);
	COMMAND(@"Print self test",[prn selfTest:FALSE error:&err]);
    COMMAND(@"Wait for job",[prn waitPrintJob:30 error:&err]);
    
    [self updateBattery];
}

- (IBAction)onBarcodesDemo:(id)sender;
{
    NSError *err;
    
	COMMAND(@"Barcode settings",[prn setBarcodeSettings:2 height:77 hriPosition:BAR_TEXT_BELOW align:ALIGN_LEFT error:&err]);
	
	COMMAND(@"Print text",[prn printText:@"UPC-A" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print barcode",[prn printBarcode:BAR_PRN_UPCA barcode:[@"12345678901" dataUsingEncoding:NSASCIIStringEncoding] error:&err]);
	COMMAND(@"Print text",[prn printText:@"\nUPC-E" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print barcode",[prn printBarcode:BAR_PRN_UPCE barcode:[@"012340000040" dataUsingEncoding:NSASCIIStringEncoding] error:&err]);
	COMMAND(@"Print text",[prn printText:@"\nJAN13(EAN)" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print barcode",[prn printBarcode:BAR_PRN_EAN13 barcode:[@"123456789012" dataUsingEncoding:NSASCIIStringEncoding] error:&err]);
	COMMAND(@"Print text",[prn printText:@"\nJAN8(EAN)" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print barcode",[prn printBarcode:BAR_PRN_EAN8 barcode:[@"1234567" dataUsingEncoding:NSASCIIStringEncoding] error:&err]);
	COMMAND(@"Print text",[prn printText:@"\nCODE 39" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print barcode",[prn printBarcode:BAR_PRN_CODE39 barcode:[@"1A1234567" dataUsingEncoding:NSASCIIStringEncoding] error:&err]);
	COMMAND(@"Print text",[prn printText:@"\nITF" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print barcode",[prn printBarcode:BAR_PRN_ITF barcode:[@"123456789012" dataUsingEncoding:NSASCIIStringEncoding] error:&err]);
	COMMAND(@"Print text",[prn printText:@"\nCODABAR (NW-7)" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print barcode",[prn printBarcode:BAR_PRN_CODABAR barcode:[@"A12356789A" dataUsingEncoding:NSASCIIStringEncoding] error:&err]);
	COMMAND(@"Print text",[prn printText:@"\nCODE 93" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print barcode",[prn printBarcode:BAR_PRN_CODE93 barcode:[@"AABCD12345" dataUsingEncoding:NSASCIIStringEncoding] error:&err]);
	COMMAND(@"Print text",[prn printText:@"\nCODE 128" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print barcode",[prn printBarcode:BAR_PRN_CODE128 barcode:[@"BABCD12345" dataUsingEncoding:NSASCIIStringEncoding] error:&err]);
	COMMAND(@"Print text",[prn printText:@"\nPDF-417" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	COMMAND(@"Print barcode",[prn printBarcode:BAR_PRN_PDF417 barcode:[@"Hey try to read this :)" dataUsingEncoding:NSASCIIStringEncoding] error:&err]);
	
	COMMAND(@"Feed paper",[prn feedPaper:0 error:&err]);
    COMMAND(@"Wait for job",[prn waitPrintJob:30 error:&err]);
    
    [self updateBattery];
}

- (IBAction)onClearLog:(id)sender
{
    [debug setString:@""];
    [self debug:@""];
}

- (IBAction)onSetEncryptionKey:(id)sender
{
    //NSData *oldKey=[@"11111111111111111111111111111111" dataUsingEncoding:NSASCIIStringEncoding]; //sample default
    NSData *oldKey=nil;
    NSData *decryptionKey=[@"11111111111111111111111111111111" dataUsingEncoding:NSASCIIStringEncoding]; //sample default
    NSError *error;
    
    if([prn cryptoSetKey:KEY_ENCRYPTION key:decryptionKey oldKey:oldKey error:&error])
        [self displayAlert:@"Success" message:@"Key was successfully set"];
    else
        [self displayAlert:@"Operation failed!" message:error.localizedDescription];
}

- (IBAction)onGraphicsDemo:(id)sender;
{
    NSError *err;
    
	COMMAND(@"Print image",[prn printImage:[UIImage imageNamed:@"taz.png"] align:ALIGN_CENTER error:&err]);
	
	COMMAND(@"Feed paper",[prn feedPaper:0 error:&err]);
    COMMAND(@"Wait for job",[prn waitPrintJob:30 error:&err]);
    
    [self updateBattery];
}

NSTimer *timerMSR=nil;

- (void)timerMSRFunc:(NSTimer*)theTimer
{
	if(statusImage.tag==0)
	{
		[statusImage setImage:[UIImage imageNamed:@"read_card.png"]];
		statusImage.tag=1;
	}
	else
	{
		[statusImage setImage:[UIImage imageNamed:@"normal_card.png"]];
		statusImage.tag=0;
	}
}

-(void)magneticCardData:(NSString *)track1 track2:(NSString *)track2 track3:(NSString *)track3;
{
    NSError *err;
    
    COMMAND(@"Print card data",[prn printText:@"{=C}{+B}MAGNETIC CARD DATA" usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
    
    if(track1 && [track1 length]>0)
        COMMAND(@"Print card data",[prn printText:([NSString stringWithFormat:@"{+B}Track1: {-B}%@",track1]) usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
    if(track2 && [track2 length]>0)
        COMMAND(@"Print card data",[prn printText:([NSString stringWithFormat:@"{+B}Track2: {-B}%@",track2]) usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
    if(track3 && [track3 length]>0)
        COMMAND(@"Print card data",[prn printText:([NSString stringWithFormat:@"{+B}Track3: {-B}%@",track3]) usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
    
	NSDictionary *card=[prn msProcessFinancialCard:track1 track2:track2];
	if(card)
	{
        COMMAND(@"Print card data",[prn feedPaper:30 error:&err]);
        
        if([card valueForKey:@"cardholderName"])
        {
            COMMAND(@"Print card data",[prn printText:([NSString stringWithFormat:@"{+B}Name:{-B}   %@",[card valueForKey:@"cardholderName"]]) error:&err]);
        }
        COMMAND(@"Print card data",[prn printText:([NSString stringWithFormat:@"{+B}Number:{-B} %@",[card valueForKey:@"accountNumber"]]) error:&err]);
        COMMAND(@"Print card data",[prn printText:([NSString stringWithFormat:@"{+B}Expires:{-B} %@/%@\n",[card valueForKey:@"expirationMonth"],[card valueForKey:@"expirationYear"]]) error:&err]);

	}
    
	COMMAND(@"Feed paper",[prn feedPaper:0 error:&err]);
    COMMAND(@"Wait for job",[prn waitPrintJob:30 error:&err]);
    
    [self updateBattery];
}

-(void)magneticCardEncryptedData:(int)encryption tracks:(int)tracks data:(NSData *)data {
	//try to decrypt the data
	
    NSData *decryptionKey=[@"11111111111111111111111111111111" dataUsingEncoding:NSASCIIStringEncoding]; //sample default
    
	//uint8_t cryptoData[]={0x88,0xD0,0xE0,0x77,0x38,0x22,0xDE,0x17,0x13,0x17,0x96,0x77,0x72,0x04,0x3C,0xFB,0xB0,0x9F,0x6A,0x84,0x63,0x82,0x2C,0xEF,0xE6,0x80,0xEA,0x97,0xBF,0x6F,0x5E,0xF4,0x86,0x30,0x4B,0x45,0xF8,0x70,0xFC,0xC5,0x27,0xEC,0x65,0x93,0x00,0x6B,0x95,0x3B,0x67,0x07,0xDC,0xE0,0x04,0x5F,0xA6,0xFD,0xFC,0x3B,0x07,0xB8,0x75,0x63,0x6C,0x18,0x31,0x28,0x50,0xF0,0xDB,0xF7,0x23,0x3E,0xF7,0x10,0xF5,0xFA,0xA9,0x8E,0x94,0x28,0x6E,0xC6,0x01,0x30,0xD5,0xAB,0xE5,0x1A,0xC9,0x60,0x90,0xBE,0xBB,0x70,0x80,0x1D,0x7E,0xFD,0xFE,0xD4,0x9E,0x2B,0xD7,0x4B,0xF7,0x1E,0x74,0xD2,0xBA,0x1A,0x30,0x64,0x84,0xD5,0x27,0xC8,0xF4,0x74,0x66,0x74,0x0C,0x6B,0x25,0xBD,0x96,0x9E,0x5C,0x12};
	//data=[NSData dataWithBytes:cryptoData length:sizeof(cryptoData)];
	NSData *decrypted=[data AESDecryptWithKey:decryptionKey];
	//basic check if the decrypted data is valid
	if(decrypted)
	{
		uint8_t *bytes=(uint8_t *)[decrypted bytes];
		for(int i=0;i<([decrypted length]-2);i++)
		{
			if(i>(4+16) && !bytes[i])
			{
				uint16_t crc16=crc16ccit(bytes,i+1,0);
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
					NSString *track1=nil,*track2=nil,*track3=nil;
					int t1=-1,t2=-1,t3=-1,tend;
					//find the tracks offset
					int dataLen=i;
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
					return;
				}
			}
		}
	}
	[self displayAlert:@"Error" message:@"Card data cannot be decrypted, possibly key is invalid"];
}

//this function is not really needed on PP-60, as automatic notifications work good enough
-(IBAction)onReadMagneticCard:(id)sender;
{
	[statusImage setImage:[UIImage imageNamed:@"normal_card.png"]];
	statusImage.tag=0;
	timerMSR=[NSTimer scheduledTimerWithTimeInterval:1.0 target:self selector:@selector(timerMSRFunc:) userInfo:nil repeats:YES];

	NSArray *tracks=[prn msReadCard:20 error:nil];
	if(tracks!=nil)
	{
        [self magneticCardData:[tracks objectAtIndex:0] track2:[tracks objectAtIndex:1] track3:[tracks objectAtIndex:2]];
	}
	[timerMSR invalidate];
	[statusImage setImage:[UIImage imageNamed:@"normal.png"]];
}

-(void)barcodeData:(NSString *)barcode type:(int)type;
{
    NSError *err;
    
	COMMAND(@"Print barcode data",[prn printText:([NSString stringWithFormat:@"{+B}Type(%d):{-B} %@",type,[prn barcodeType2Text:type]]) usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
    COMMAND(@"Print barcode data",[prn printText:([NSString stringWithFormat:@"{+B}Barcode:{-B} %@",barcode]) usingEncoding:NSWindowsCP1252StringEncoding error:&err]);
	
	COMMAND(@"Feed paper",[prn feedPaper:0 error:&err]);
    COMMAND(@"Wait for job",[prn waitPrintJob:30 error:&err]);
    
    [self updateBattery];
}

//on PP-60 barcode can be scanned by using the hardware buttons and automatic notification is received
-(IBAction)onScanBarcode:(id)sender;
{
	int barcode_type;
	
	[statusImage setImage:[UIImage imageNamed:@"scan.png"]];
	[[NSRunLoop currentRunLoop] runUntilDate:[NSDate dateWithTimeIntervalSinceNow:0.1]];
    
	NSString *barcode=[prn scanBarcode:&barcode_type timeout:6.0 error:nil];
    
    if(barcode)
        [self barcodeData:barcode type:barcode_type];
    
	[statusImage setImage:[UIImage imageNamed:@"normal.png"]];
}

-(IBAction)onLoadLogo:(id)sender;
{
    NSError *err;
    
    COMMAND(@"Load logo",[prn loadLogo:[UIImage imageNamed:@"Icon-72.png"] align:ALIGN_CENTER error:&err]);
    COMMAND(@"Print logo",[prn printLogo:LOGO_NORMAL error:&err]);

	COMMAND(@"Feed paper",[prn feedPaper:0 error:&err]);
}


/*
	 // The designated initializer. Override to perform setup that is required before the view is loaded.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
        // Custom initialization
    }
    return self;
}
*/

/*
// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
}
*/

// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
	debug=[[NSMutableString alloc] init];
#ifdef LOG_FILE
	NSFileManager *fileManger = [NSFileManager defaultManager];
	if ([fileManger fileExistsAtPath:[self getLogFile]])
	{
		[debug appendString:[[[NSString alloc] initWithContentsOfFile:[self getLogFile]] autorelease]];
		[debugText setText:debug];
	}
#endif
	prn=[Printer sharedDevice];
	prn.delegate=self;
	[prn connect];
	[super viewDidLoad];
}



/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
	[prn disconnect];
}

@end
