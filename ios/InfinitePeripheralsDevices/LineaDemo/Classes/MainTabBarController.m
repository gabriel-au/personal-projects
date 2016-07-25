#import "MainTabBarController.h"
#import "NSDataCrypto.h"

@implementation MainTabBarController

-(void)enableCharging {
    [linea setCharging:[[NSUserDefaults standardUserDefaults] boolForKey:@"AutoCharging"] error:nil];
}

-(void)connectionState:(int)state {
    NSError *error=nil;
    
	switch (state) {
		case CONN_DISCONNECTED:
		case CONN_CONNECTING:
#if TARGET_IPHONE_SIMULATOR
			[self setViewControllers:[NSArray arrayWithObjects:scannerViewController,settingsViewController,cryptoViewController,rfViewController,emsrCryptoViewController,nil] animated:TRUE];
#else
            //[self performSelectorOnMainThread:@selector(enableCharging) withObject:nil waitUntilDone:NO];
            
			[self setViewControllers:[NSArray arrayWithObject:scannerViewController] animated:FALSE];
#endif
			break;
		case CONN_CONNECTED:
			SHOWERR([linea msStartScan:&error]);
            //keep the egine on by default, this is useful for 2D barcode engine that takes several seconds to power on
            if(![[NSUserDefaults standardUserDefaults] objectForKey:@"BarcodeEngineOn"])
            {
                [[NSUserDefaults standardUserDefaults] setBool:TRUE forKey:@"BarcodeEngineOn"];
                [[NSUserDefaults standardUserDefaults] synchronize];
            }
            SHOWERR([linea setBarcodeTypeMode:BARCODE_TYPE_EXTENDED error:&error]);

            SHOWERR([linea barcodeEnginePowerControl:[[NSUserDefaults standardUserDefaults] boolForKey:@"BarcodeEngineOn"] maxTimeMinutes:1 error:&error]);
            
            //setting various opticon engine parameters
            //SHOWERR([linea barcodeOpticonSetInitString:@"V4[D01[DM2[D00" error:&error]);
            //SHOWERR([linea barcodeOpticonSetInitString:@"[DM2[D00YQ[BCDE6" error:&error]);
            /*const uint8_t codeInit[]=
            {
                0x41, //start
                0x7B,0x46,7, //set the illumination to 7%, do not go over 40%
                0x4f,0x40,1, //enable gs1 databar omnidirection
                0x4f,0x41,1, //enable gs1 databar limited
                0x4f,0x42,1, //enable gs1 databar extended
                0x4c,0x42,1, //enable micro pdf417
            };
            SHOWERR([linea barcodeIntermecSetInitData:[NSData dataWithBytes:codeInit length:sizeof(codeInit)] error:&error]);
            */
            
            //encrypted head, you can check supported algorithms and select the one you want
            if([linea.deviceModel rangeOfString:@"CM"].location!=NSNotFound)
            {
                NSNumber *emsrAlgorithm=[[NSUserDefaults standardUserDefaults] objectForKey:@"emsrAlgorithm"];
                if(emsrAlgorithm==nil)
                    emsrAlgorithm=[NSNumber numberWithInt:ALG_EH_AES256];
                [linea emsrSetEncryption:[emsrAlgorithm intValue]+1 params:nil error:nil];
                [linea emsrConfigMaskedDataShowExpiration:TRUE unmaskedDigitsAtStart:4 unmaskedDigitsAtEnd:2 error:nil];
                //NSArray *supported=[linea emsrGetSupportedEncryptions:&error];
                //[linea emsrSetEncryption:ALG_EH_IDTECH params:nil error:nil];
            }
            
//            SHOWERR([linea barcodeCodeSetParam:0x29 value:0 error:&error]);
            
			//calling this function last, after all notifications has been called in all registered deleegates,
			//because enabling/disabling charge in firmware versions <2.34 will force disconnect and reconnect
			[self performSelectorOnMainThread:@selector(enableCharging) withObject:nil waitUntilDone:NO];
			
			[self setViewControllers:[NSArray arrayWithObjects:scannerViewController,settingsViewController,cryptoViewController,rfViewController,emsrCryptoViewController,nil] animated:FALSE];
			break;
	}
}

-(void)viewWillAppear:(BOOL)animated
{
}

-(void)viewWillDisappear:(BOOL)animated
{
}

-(void)viewDidLoad
{
	//init linea class and connect it
	linea=[Linea sharedDevice];
	[linea addDelegate:self];
	[linea connect];
    [super viewDidLoad];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    if(UI_USER_INTERFACE_IDIOM()==UIUserInterfaceIdiomPad)
        if(interfaceOrientation==UIInterfaceOrientationPortraitUpsideDown || interfaceOrientation==UIInterfaceOrientationPortrait)
            return YES;
    
    return NO;
}

-(void)dealloc
{
	[linea disconnect];
}

@end
