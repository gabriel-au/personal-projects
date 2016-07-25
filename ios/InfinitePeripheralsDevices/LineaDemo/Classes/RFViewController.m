#import "RFViewController.h"


@implementation RFViewController

-(void)displayAlert:(NSString *)title message:(NSString *)message
{
	UIAlertView *alert = [[UIAlertView alloc] initWithTitle:title message:message delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
	[alert show];
}

#define RF_COMMAND(operation,c) {if(!c){[self displayAlert:@"Operatin failed!" message:[NSString stringWithFormat:@"%@ failed, error %@, code: %d",operation,error.localizedDescription,error.code]]; return;} }

static BOOL stringToHex(NSString *str, uint8_t *data, int length)
{
    NSString *t=[[str stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceCharacterSet]] lowercaseString];
    if([t length]<(length*3-1))
        return FALSE;
    for(int i=0;i<[t length];i++)
    {
        char c=[t characterAtIndex:i];
        if((c<'0' || c>'9') && (c<'a' || c>'f') && c!=' ')
            return FALSE;
    }
        
    for(int i=0;i<length;i++)
    {
        char c=[t characterAtIndex:i*3];
        if(c>='a')
            data[i]=c-'a'+10;
        else
            data[i]=c-'0';
        data[i]<<=4;
        
        c=[t characterAtIndex:i*3+1];
        if(c>='a')
            data[i]|=c-'a'+10;
        else
            data[i]|=c-'0';
    }
    return true;
}

static NSString *hexToString(NSString * label, void *data, int length)
{
	const char HEX[]="0123456789ABCDEF";
	char s[2000];
	for(int i=0;i<length;i++)
	{
		s[i*3]=HEX[((uint8_t *)data)[i]>>4];
		s[i*3+1]=HEX[((uint8_t *)data)[i]&0x0f];
		s[i*3+2]=' ';
	}
	s[length*3]=0;
	
    if(label)
        return [NSString stringWithFormat:@"%@(%d): %s",label,length,s];
    else
        return [NSString stringWithCString:s encoding:NSASCIIStringEncoding];
}

-(IBAction)clear:(id)sender
{
    [logView setText:@""];
}

-(void)rfCardRemoved:(int)cardIndex
{
    [logView setText:@""];
}

#define CHECK_RESULT(description,result) if(result)[s appendFormat:@"%@: SUCCESS\n",description]; else [s appendFormat:@"%@: FAILED (%@)\n",description,error.localizedDescription];
-(void)rfCardDetected:(int)cardIndex info:(DTRFCardInfo *)info
{
    NSError *error;
    
    NSMutableString *s=[[NSMutableString alloc] init];
    [s appendFormat:@"%@ card detected\n",info.typeStr];
    [s appendFormat:@"Serial: %@\n",hexToString(nil,(uint8_t *)info.UID.bytes,info.UID.length)];
    switch (info.type)
    {
        case CARD_MIFARE_MINI:
        case CARD_MIFARE_CLASSIC_1K:
        case CARD_MIFARE_CLASSIC_4K:
        case CARD_MIFARE_PLUS:
        {//16 bytes reading and 16 bytes writing
            //try to authenticate first with default key
            const uint8_t key[]={0xFF,0xFF,0xFF,0xFF,0xFF,0xFF};
            //it is best to store the keys you are going to use once in the device memory, then use mfAuthByStoredKey function to authenticate blocks rahter than having the key in your program
            BOOL r=[linea mfAuthByKey:cardIndex type:'A' address:8 key:[NSData dataWithBytes:key length:sizeof(key)] error:&error];
            CHECK_RESULT(@"Authenticate",r);
            //try reading a block we authenticated before
            NSData *block=[linea mfRead:cardIndex address:8 length:16 error:&error];
            CHECK_RESULT(@"Read block",block);
            if(block)
                [s appendFormat:@"Data: %@\n",hexToString(nil,(uint8_t *)block.bytes,block.length)];
            //write something, be VERY cautious where you write, as you can easily render the card useless forever
            //const uint8_t dataToWrite[16]={0x00,0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09,0x0A,0x0B,0x0C,0x0D,0x0E,0x0F};
            //r=[linea mfWrite:cardIndex address:8 data:[NSData dataWithBytes:dataToWrite length:sizeof(dataToWrite)] error:&error];
            //CHECK_RESULT(@"Write block",r);
            break;
        }
        case CARD_MIFARE_ULTRALIGHT:
        {//16 bytes reading, 4 bytes writing
            //try reading a block
            NSData *block=[linea mfRead:cardIndex address:8 length:16 error:&error];
            CHECK_RESULT(@"Read block",block);
            if(block)
                [s appendFormat:@"Data: %@\n",hexToString(nil,(uint8_t *)block.bytes,block.length)];
            //write something to the card
            const uint8_t dataToWrite[4]={0x00,0x01,0x02,0x03};
            int r=[linea mfWrite:cardIndex address:8 data:[NSData dataWithBytes:dataToWrite length:sizeof(dataToWrite)] error:&error];
            CHECK_RESULT(@"Write block",r);
            break;
        }
        case CARD_MIFARE_ULTRALIGHT_C:
        {//16 bytes reading, 4 bytes writing, authentication may be required
            //try reading a block we authenticated before
            NSData *block=[linea mfRead:cardIndex address:8 length:16 error:&error];
            CHECK_RESULT(@"Read block",block);
            if(block)
                [s appendFormat:@"Data: %@\n",hexToString(nil,(uint8_t *)block.bytes,block.length)];
            //write something to the card
            const uint8_t dataToWrite[4]={0x00,0x01,0x02,0x03};
            int r=[linea mfWrite:cardIndex address:8 data:[NSData dataWithBytes:dataToWrite length:sizeof(dataToWrite)] error:&error];
            CHECK_RESULT(@"Write block",r);
            break;
        }
        case CARD_ISO15693:
        {//block size is different between cards
            [s appendFormat:@"Block size: %d\n",info.blockSize];
            [s appendFormat:@"Number of blocks: %d\n",info.nBlocks];
            //try reading 2 blocks
            NSData *block=[linea iso15693Read:cardIndex startBlock:0 length:info.blockSize error:&error];
            CHECK_RESULT(@"Read blocks",block);
            if(block)
                [s appendFormat:@"Data: %@\n",hexToString(nil,(uint8_t *)block.bytes,block.length)];
            //write something to the card
            const uint8_t dataToWrite[4]={0x00,0x01,0x02,0x03};
            int r=[linea iso15693Write:cardIndex startBlock:0 data:[NSData dataWithBytes:dataToWrite length:sizeof(dataToWrite)] error:&error];
            CHECK_RESULT(@"Write blocks",r);
            break;
        }
    }
    [s appendFormat:@"Please remove card"];
    [logView setText:s];
    [linea rfRemoveCard:cardIndex error:nil];
}

-(void)viewWillAppear:(BOOL)animated
{
    NSError *error;
    RF_COMMAND(@"RF Init",[linea rfInit:CARD_SUPPORT_TYPE_A|CARD_SUPPORT_ISO15 error:&error]);
}

-(void)viewWillDisappear:(BOOL)animated
{
    [linea rfClose:nil];
}

-(void)viewDidLoad
{
	linea=[Linea sharedDevice];
    [linea addDelegate:self];
    [super viewDidLoad];
}


@end
