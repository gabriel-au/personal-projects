#import "SettingsViewController.h"
#import "NSDataCrypto.h"

@implementation SettingsViewController

@synthesize selectedPrinterName;
@synthesize selectedPrinterAddress;
@synthesize firmwareFile;
@synthesize scanMode;

static NSString *settings[]={
	@"Beep upon scan",
	@"Enable scan button",
	@"Magnetic card raw mode",
	@"Synchronization enabled", 
	@"Automated charge enabled",
	@"Barcode engine always on"
};

static NSString *scan_modes[]={
	@"Single scan",
	@"Multi scan",
	@"Motion detect",
	@"Single scan on button release",
    @"Multi scan without duplicates",
};


enum SETTINGS{
	SET_BEEP=0,
	SET_ENABLE_SCAN_BUTTON,
	SET_MSR_RAW,
	SET_SYNC_ENABLED,
	SET_AUTOCHARGING,
	SET_ENGINE_ON,
    SET_LAST
};
	
static BOOL settings_values[SET_LAST];
//static int scanMode;

#define TARGET_LINEA 0
#define TARGET_EMSR 1
#define TARGET_OPTICON 2
#define TARGET_CODE 3

int beep1[]={2730,250};
int beep2[]={2730,150,65000,20,2730,150};

-(void)displayAlert:(NSString *)title message:(NSString *)message
{
	UIAlertView *alert = [[UIAlertView alloc] initWithTitle:title message:message delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil];
	[alert show];
}

-(NSString *)getLineaFirmwareFileName
{
	NSError *error;
	NSString *name=[[linea.deviceName stringByReplacingOccurrencesOfString:@" " withString:@""] lowercaseString];
	NSArray *files=[[NSFileManager defaultManager] contentsOfDirectoryAtPath:[[NSBundle mainBundle] resourcePath] error:&error];
	int lastVer=0;
	NSString *lastPath;
	for(int i=0;i<[files count];i++)
	{
		NSString *file=[[files objectAtIndex:i] lastPathComponent];
		if([[file lowercaseString] rangeOfString:name].location!=NSNotFound)
		{
			NSString *path=[[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent:file];
            NSDictionary *info=[linea getFirmwareFileInformation:[NSData dataWithContentsOfFile:path] error:&error];
            if(info)
                NSLog(@"file: %@, name=%@, model=%@",file,[info objectForKey:@"deviceName"],[info objectForKey:@"deviceModel"]);
			if(info && [[info objectForKey:@"deviceName"] isEqualToString:linea.deviceName] && [[info objectForKey:@"deviceModel"] isEqualToString:linea.deviceModel] && [[info objectForKey:@"firmwareRevisionNumber"] intValue]>lastVer)
			{
				lastPath=path;
				lastVer=[[info objectForKey:@"firmwareRevisionNumber"] intValue];
			}
		}
	}
	if(lastVer>0)
		return lastPath;
	return nil;
}

-(NSString *)getEMSRFirmwareFileName
{
	NSError *error;
    
	NSArray *files=[[NSFileManager defaultManager] contentsOfDirectoryAtPath:[[NSBundle mainBundle] resourcePath] error:&error];
//	NSString *name=[[[linea emsrGetDeviceModel:nil] stringByReplacingOccurrencesOfString:@" " withString:@""] lowercaseString];
    NSString *name=@"emsr";
    if(!name)
        return nil;
    
	int lastVer=0;
	NSString *lastPath;
	for(int i=0;i<[files count];i++)
	{
		NSString *file=[[[files objectAtIndex:i] lastPathComponent] lowercaseString];
		if([file rangeOfString:name].location!=NSNotFound)
		{
			NSString *path=[[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent:[files objectAtIndex:i]];

            NSDictionary *info=[linea emsrGetFirmwareInformation:[NSData dataWithContentsOfFile:path] error:&error];
			if(info /*&& [[info objectForKey:@"deviceName"] isEqualToString:[linea emsrGetDeviceModel]] && [[info objectForKey:@"firmwareRevisionNumber"] intValue]>lastVer*/)
			{
				lastPath=path;
				lastVer=[[info objectForKey:@"firmwareRevisionNumber"] intValue];
			}
		}
	}
	if(lastVer>0)
		return lastPath;
	return nil;
}

-(void)connectionState:(int)state {
    NSError *error;
    
	switch (state) {
		case CONN_DISCONNECTED:
		case CONN_CONNECTING:
			break;
		case CONN_CONNECTED:
			//set defaults
			settings_values[SET_BEEP]=TRUE;
			
			//read settings
            int value;
			if([linea getScanButtonMode:&value error:&error])
                settings_values[SET_ENABLE_SCAN_BUTTON]=(value==BUTTON_ENABLED);
            else
                settings_values[SET_ENABLE_SCAN_BUTTON]=FALSE;
            
			if([linea getMSCardDataMode:&value error:&error])
                settings_values[SET_MSR_RAW]=(value==MS_RAW_CARD_DATA);
            else
                settings_values[SET_MSR_RAW]=FALSE;
            
			if([linea getSyncButtonMode:&value error:&error])
                settings_values[SET_SYNC_ENABLED]=(value==BUTTON_ENABLED);
            else
                settings_values[SET_SYNC_ENABLED]=FALSE;
            
			settings_values[SET_AUTOCHARGING]=[[NSUserDefaults standardUserDefaults] boolForKey:@"AutoCharging"];
			settings_values[SET_ENGINE_ON]=[[NSUserDefaults standardUserDefaults] boolForKey:@"BarcodeEngineOn"];

            if(![linea getScanMode:&scanMode error:&error])
                scanMode=0;
			
			[settingsTable reloadData];
			break;
	}
}

-(void)firmwareUpdateEnd:(NSError *)error
{
    [progressViewController.view removeFromSuperview];
    if(error)
        [self displayAlert:NSLocalizedString(@"Firmware Update",nil) message:[NSString stringWithFormat:NSLocalizedString(@"Firmware updated failed with error:%@",nil),error.localizedDescription]];
}

-(void)firmwareUpdateDisplayProgress
{
    switch (progressPhase)
    {
        case UPDATE_INIT:
            [progressViewController updateProgress:NSLocalizedString(@"Initializing update...",nil) progress:progressPercent];
            break;
        case UPDATE_ERASE:
            [progressViewController updateProgress:NSLocalizedString(@"Erasing flash...",nil) progress:progressPercent];
            break;
        case UPDATE_WRITE:
            [progressViewController updateProgress:NSLocalizedString(@"Writing firmware...",nil) progress:progressPercent];
            break;
        case UPDATE_COMPLETING:
            [progressViewController updateProgress:NSLocalizedString(@"Completing operation...",nil) progress:progressPercent];
            break;
        case UPDATE_FINISH:
            [progressViewController updateProgress:NSLocalizedString(@"Complete!",nil) progress:progressPercent];
            break;
    }
}
    
-(void)firmwareUpdateProgress:(int)phase percent:(int)percent
{
    progressPhase=phase;
    progressPercent=percent;
    [self performSelectorOnMainThread:@selector(firmwareUpdateDisplayProgress) withObject:nil waitUntilDone:FALSE];
}

-(void)firmwareUpdateThread:(NSString *)file
{
	@autoreleasepool {
        
        NSError *error=nil;
    
        BOOL idleTimerDisabled_Old=[UIApplication sharedApplication].idleTimerDisabled;
        
        [[UIApplication sharedApplication] setIdleTimerDisabled: YES];
        
        if(fwUpdateTarget==TARGET_LINEA)
        {
            //In case authentication key is present in the Linea, we need to authenticate with it first, before firmware update is allowed
            //For the sample here I'm using the field "Authentication key" in the crypto settings as data and generally ignoring the result of the
            //authentication operation, firmware update will just fail if authentication have failed
            NSUserDefaults *prefs = [NSUserDefaults standardUserDefaults];
            //last used decryption key is stored in preferences
            NSString *authenticationKey=[prefs objectForKey:@"AuthenticationKey"];
            if(authenticationKey==nil || authenticationKey.length!=32)
                authenticationKey=@"11111111111111111111111111111111"; //sample default
            
            [linea cryptoAuthenticateiPod:[authenticationKey dataUsingEncoding:NSASCIIStringEncoding] error:nil];
            [linea updateFirmwareData:[NSData dataWithContentsOfFile:file] error:&error];
        }
        if(fwUpdateTarget==TARGET_EMSR)
        {
            [linea emsrUpdateFirmware:[NSData dataWithContentsOfFile:file] error:&error];
        }
        if(fwUpdateTarget==TARGET_OPTICON)
        {
            NSString *file05=[[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent:@"Opticon_FL49J05.bin"];
            NSString *fileBoot=[[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent:@"Opticon_BOOT.bin"];
            NSString *file09=[[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent:@"Opticon_FL49J09.bin"];
            //NSString *file08Multi=[[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent:@"Opticon_FL49J08_SBEV0005e.bin"];
            NSFileManager *fileManager=[NSFileManager defaultManager];
            
            if([fileManager fileExistsAtPath:file05] && [fileManager fileExistsAtPath:fileBoot] && [fileManager fileExistsAtPath:file09])
            {
//                [progressViewController performSelectorOnMainThread:@selector(updateText:) withObject:@"Updating to version FL49J05...\nPlease wait!" waitUntilDone:NO];
                
//                [linea barcodeOpticonUpdateFirmware:[NSData dataWithContentsOfFile:file08Multi] bootLoader:FALSE error:&error];
//                if([linea barcodeOpticonUpdateFirmware:[NSData dataWithContentsOfFile:file05] bootLoader:FALSE error:&error])
//                {
//                    [progressViewController performSelectorOnMainThread:@selector(updateText:) withObject:@"Updating Bootloader code...\nPlease wait!" waitUntilDone:NO];
//                    if([linea barcodeOpticonUpdateFirmware:[NSData dataWithContentsOfFile:fileBoot] bootLoader:TRUE error:&error])
//                    {
                        [progressViewController performSelectorOnMainThread:@selector(updateText:) withObject:@"Updating to version Opticon_FL49J09...\nPlease wait!" waitUntilDone:NO];
                        [linea barcodeOpticonUpdateFirmware:[NSData dataWithContentsOfFile:file09] bootLoader:FALSE error:&error];
//                    }
//                }
            }
            //power off/on the engine
            [linea barcodeEnginePowerControl:FALSE error:nil];
            [linea barcodeEnginePowerControl:settings_values[SET_ENGINE_ON] maxTimeMinutes:30 error:nil];
        }
        if(fwUpdateTarget==TARGET_CODE)
        {
            [progressViewController performSelectorOnMainThread:@selector(updateText:) withObject:@"Updating engine...\nPlease wait!" waitUntilDone:NO];
            [linea barcodeCodeUpdateFirmware:[self.firmwareFile lastPathComponent] data:[NSData dataWithContentsOfFile:self.firmwareFile] error:&error];
        }

        [[UIApplication sharedApplication] setIdleTimerDisabled: idleTimerDisabled_Old];
        [self performSelectorOnMainThread:@selector(firmwareUpdateEnd:) withObject:error waitUntilDone:FALSE];
    
    }
}

-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
	if(buttonIndex == 1)
	{
        //Make firmware update prettier - call it from a thread and listen to the notifications only
        [progressViewController viewWillAppear:FALSE];
        [self.view addSubview:progressViewController.view];
        
        [NSThread detachNewThreadSelector:@selector(firmwareUpdateThread:) toTarget:self withObject:firmwareFile];
	}
}

-(void)checkForLineaFirmwareUpdate;
{
	self.firmwareFile=[self getLineaFirmwareFileName];
	if(self.firmwareFile==nil)
	{
		UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
														message:NSLocalizedString(@"No firmware for this device model present",nil) delegate:nil cancelButtonTitle:NSLocalizedString(@"Ok",nil) otherButtonTitles:nil, nil];
		[alert show];
	}else {
        NSDictionary *info=[linea getFirmwareFileInformation:[NSData dataWithContentsOfFile:self.firmwareFile] error:nil];
		
		if(info && [[info objectForKey:@"deviceName"] isEqualToString:linea.deviceName] && [[info objectForKey:@"deviceModel"] isEqualToString:linea.deviceModel])
		{
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
                                                            message:[NSString stringWithFormat:NSLocalizedString(@"Linea ver: %@\nAvailable: %@\n\nDo you want to update firmware?\n\nDO NOT DISCONNECT LINEA DURING FIRMWARE UPDATE!",nil),[linea firmwareRevision],[info objectForKey:@"firmwareRevision"]]
                                                           delegate:self cancelButtonTitle:NSLocalizedString(@"Cancel",nil) otherButtonTitles:NSLocalizedString(@"Update",nil), nil];
            [alert show];
		}else {
			UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
															message:NSLocalizedString(@"No firmware for this device model present",nil) delegate:nil cancelButtonTitle:NSLocalizedString(@"Ok",nil) otherButtonTitles:nil, nil];
			[alert show];
		}
	}
}

-(void)checkForEMSRFirmwareUpdate;
{
	self.firmwareFile=[self getEMSRFirmwareFileName];
	if(self.firmwareFile==nil)
	{
		UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
														message:NSLocalizedString(@"No firmware for this device model present",nil) delegate:nil cancelButtonTitle:NSLocalizedString(@"Ok",nil) otherButtonTitles:nil, nil];
		[alert show];
	}else {
        NSDictionary *info=[linea emsrGetFirmwareInformation:[NSData dataWithContentsOfFile:self.firmwareFile] error:nil];
		
		//if([[info objectForKey:@"deviceModel"] isEqualToString:[linea emsrGetDeviceModel:nil]])
		{
            int emsrFWVersion=0;
            [linea emsrGetFirmwareVersion:&emsrFWVersion error:nil];
            
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
                                                            message:[NSString stringWithFormat:NSLocalizedString(@"Head ver: %d.%02d\nAvailable: %@\n\nDo you want to update firmware?\n\nDO NOT DISCONNECT LINEA DURING FIRMWARE UPDATE!",nil),emsrFWVersion/100,emsrFWVersion%100,[info objectForKey:@"firmwareRevision"]]
                                                           delegate:self cancelButtonTitle:NSLocalizedString(@"Cancel",nil) otherButtonTitles:NSLocalizedString(@"Update",nil), nil];
            [alert show];
		}
//        else {
//			UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
//															message:NSLocalizedString(@"No firmware for this device model present",nil) delegate:nil cancelButtonTitle:NSLocalizedString(@"Ok",nil) otherButtonTitles:nil, nil];
//			[alert show];
//		}
	}
}

-(void)checkForOpticonFirmwareUpdate;
{
    self.firmwareFile=[[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent:@"Opticon_FL49J05.bin"];
    NSString *opticonIdent=[linea barcodeOpticonGetIdent:nil];
    
	if(self.firmwareFile==nil)
	{
		UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
														message:NSLocalizedString(@"No firmware for this device model present",nil) delegate:nil cancelButtonTitle:NSLocalizedString(@"Ok",nil) otherButtonTitles:nil, nil];
		[alert show];
	}else
    {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
                                                        message:[NSString stringWithFormat:NSLocalizedString(@"Current engine firmware: %@\n\nDo you want to update firmware?\n\nDO NOT DISCONNECT LINEA DURING FIRMWARE UPDATE!",nil),opticonIdent]
                                                       delegate:self cancelButtonTitle:NSLocalizedString(@"Cancel",nil) otherButtonTitles:NSLocalizedString(@"Update",nil), nil];
        [alert show];
	}
}

-(void)checkForCodeFirmwareUpdate;
{
    self.firmwareFile=[[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent:@"C005922_0288-system-cr8000-CD_GEN.CRZ"];
	if(self.firmwareFile==nil)
	{
		UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
														message:NSLocalizedString(@"No firmware for this device model present",nil) delegate:nil cancelButtonTitle:NSLocalizedString(@"Ok",nil) otherButtonTitles:nil, nil];
		[alert show];
    }else
    {
        NSDictionary *info=[linea barcodeCodeGetInformation:nil];
        if(!info)
        {
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
                                                            message:NSLocalizedString(@"Code engine not present or not responding",nil) delegate:nil cancelButtonTitle:NSLocalizedString(@"Ok",nil) otherButtonTitles:nil, nil];
            [alert show];
        }else
        {
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"Firmware Update",nil)
                                                            message:[NSString stringWithFormat:@"Reader info:\n%@\nDo you want to update engine firmware?",info]
                                                           delegate:self cancelButtonTitle:NSLocalizedString(@"Cancel",nil) otherButtonTitles:NSLocalizedString(@"Update",nil), nil];
            [alert show];
        }
    }
}

-(void)bluetoothDeviceDiscovered:(NSString *)btAddress name:(NSString *)btName
{
    [printers addObject:btAddress];
    [printers addObject:btName];
}

-(void)bluetoothDiscoverComplete:(BOOL)success
{
    [progressViewController.view removeFromSuperview];
    [linea btmSetEnabled:FALSE error:nil];
    [settingsTable reloadData];
    if(!success)
        [self displayAlert:NSLocalizedString(@"Bluetooth Error",nil) message:NSLocalizedString(@"Printer discovery failed!",nil)];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    // Number of sections is the number of region dictionaries
    return 4;
}

- (NSString *)tableView:(UITableView *)aTableView titleForHeaderInSection:(NSInteger)section {
	switch (section)
	{
		case 0:
			return NSLocalizedString(@"General Settings",nil);
		case 1:
			return NSLocalizedString(@"Barcode Scan Mode",nil);
		case 2:
			return NSLocalizedString(@"Bluetooth Printers",nil);
		case 3:
			return NSLocalizedString(@"Firmware Update",nil);
	}
	return @"";
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    // Number of rows is the number of names in the region dictionary for the specified section
	switch (section)
	{
		case 0:
			return SET_LAST;
		case 1:
			return 5;
		case 2:
			return [printers count]/2+1;
		case 3:
			return 4;
	}
	return 0;
}

NSString *getLogFile()
{
	NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
	return [[paths objectAtIndex:0] stringByAppendingPathComponent:@"random.bin"];
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSError *error;
    
	if(linea.connstate!=CONN_CONNECTED)
		return;
	
	switch ([indexPath indexAtPosition:0])
	{
		case 0:
        {
			if(settings_values[indexPath.row])
			{
				settings_values[indexPath.row]=FALSE;
			}else
			{
				settings_values[indexPath.row]=TRUE;
			}
			switch (indexPath.row)
            {
                case SET_BEEP:
                    if(settings_values[SET_BEEP])
                    {
                        [linea setScanBeep:settings_values[SET_BEEP] volume:100 beepData:beep2 length:sizeof(beep2) error:nil]; 
                    }else
                    {
                        [linea setScanBeep:settings_values[SET_BEEP] volume:0 beepData:nil length:0 error:nil]; 
                    }
                    break;
                case SET_ENABLE_SCAN_BUTTON:
                    [linea setScanButtonMode:settings_values[SET_ENABLE_SCAN_BUTTON] error:nil];
                    break;
                case SET_MSR_RAW:
                    [linea setMSCardDataMode:settings_values[SET_MSR_RAW] error:nil];
                    break;
                case SET_SYNC_ENABLED:
                    [linea setSyncButtonMode:settings_values[SET_SYNC_ENABLED] error:nil];
                    break;
                case SET_AUTOCHARGING:
                {
                    [[NSUserDefaults standardUserDefaults] setBool:settings_values[SET_AUTOCHARGING] forKey:@"AutoCharging"];
                    [[NSUserDefaults standardUserDefaults] synchronize];
                    [linea setCharging:settings_values[SET_AUTOCHARGING] error:nil];
                    break;
                }
                case SET_ENGINE_ON:
                    [[NSUserDefaults standardUserDefaults] setBool:settings_values[SET_ENGINE_ON] forKey:@"BarcodeEngineOn"];
                    [[NSUserDefaults standardUserDefaults] synchronize];
                    [linea barcodeEnginePowerControl:settings_values[SET_ENGINE_ON] maxTimeMinutes:30 error:nil];
                    break;
            }
			[[tableView cellForRowAtIndexPath: indexPath] setAccessoryType:settings_values[indexPath.row]?UITableViewCellAccessoryCheckmark:UITableViewCellAccessoryNone];
			break;
        }
        case 1:
            if([linea setScanMode:indexPath.row error:nil])
                scanMode=indexPath.row;
            [tableView reloadData];
            break;
		case 2:
        {
			if(indexPath.row)
			{
				selectedPrinterAddress=[printers objectAtIndex:(indexPath.row-1)*2];
                selectedPrinterName=[printers objectAtIndex:(indexPath.row-1)*2+1];
                NSUserDefaults *prefs = [NSUserDefaults standardUserDefaults];
                [prefs setObject:selectedPrinterAddress forKey:@"selectedPrinterAddress"];
                [prefs setObject:selectedPrinterName forKey:@"selectedPrinterName"];
                [prefs synchronize];
                [tableView reloadData];
			}else {
				//scan for printers
                [progressViewController viewWillAppear:FALSE];
				[self.view addSubview:progressViewController.view];
				[[NSRunLoop currentRunLoop] runUntilDate:[NSDate dateWithTimeIntervalSinceNow:0.01]];
				if([linea btmSetEnabled:TRUE error:&error])
                {
                    [printers removeAllObjects];
                    [linea btDiscoverPrintersInBackground:10 maxTime:10.0 error:&error];
                }else {
                    [progressViewController.view removeFromSuperview];
                    ERRMSG(NSLocalizedString(@"Bluetooth Error",nil));
                }
			}
			break;
        }
		case 3:
        {
            fwUpdateTarget=indexPath.row;
            if(fwUpdateTarget==TARGET_LINEA)
                [self checkForLineaFirmwareUpdate];
            if(fwUpdateTarget==TARGET_EMSR)
                [self checkForEMSRFirmwareUpdate];
            if(fwUpdateTarget==TARGET_OPTICON)
                [self checkForOpticonFirmwareUpdate];
            if(fwUpdateTarget==TARGET_CODE)
                [self checkForCodeFirmwareUpdate];
			break;
        }
	}
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
	CGRect CellFrame = CGRectMake(0, 0, 300, 60);
	UITableViewCell *cell=[[UITableViewCell alloc] initWithFrame:CellFrame];
	
	switch ([indexPath indexAtPosition:0])
	{
		case 0:
        {
			if(settings_values[indexPath.row])
				cell.accessoryType=UITableViewCellAccessoryCheckmark;
			else
				cell.accessoryType=UITableViewCellAccessoryNone;
			[[cell textLabel] setText:NSLocalizedString(settings[indexPath.row],nil)];
			return cell;
        }
		case 1:
        {
			if(scanMode==indexPath.row)
				cell.accessoryType=UITableViewCellAccessoryCheckmark;
			else
				cell.accessoryType=UITableViewCellAccessoryNone;
			[[cell textLabel] setText:NSLocalizedString(scan_modes[indexPath.row],nil)];
			return cell;
        }
		case 2:
        {
			if(indexPath.row)
			{
				[[cell textLabel] setText:[NSString stringWithFormat:@"%@ (%@)",[printers objectAtIndex:(indexPath.row-1)*2+1],[printers objectAtIndex:(indexPath.row-1)*2]]];
				if(selectedPrinterAddress && [[printers objectAtIndex:(indexPath.row-1)*2] isEqualToString:selectedPrinterAddress])
					cell.accessoryType=UITableViewCellAccessoryCheckmark;
				else
					cell.accessoryType=UITableViewCellAccessoryNone;
			}else
				[[cell textLabel] setText:NSLocalizedString(@"Discover printers",nil)];
			return cell;
        }
		case 3:
        {
			switch (indexPath.row)
            {
                case 0:
                    [[cell textLabel] setText:NSLocalizedString(@"Update firmware",nil)];
                    break;
                case 1:
                    [[cell textLabel] setText:NSLocalizedString(@"Update Encrypted Head firmware",nil)];
                    break;
                case 2:
                    [[cell textLabel] setText:NSLocalizedString(@"Update opticon firmware",nil)];
                    break;
                case 3:
                    [[cell textLabel] setText:NSLocalizedString(@"Update code firmware",nil)];
                    break;
            }
        }
			return cell;
	}
	return nil;	
}

- (void)viewWillAppear:(BOOL)animated
{
	NSUserDefaults *prefs = [NSUserDefaults standardUserDefaults];
	selectedPrinterAddress=[prefs objectForKey:@"selectedPrinterAddress"];
	selectedPrinterName=[prefs objectForKey:@"selectedPrinterName"];
    if(![printers count] && selectedPrinterAddress)
    {
        [printers addObject:selectedPrinterAddress];
        [printers addObject:selectedPrinterName];
        [settingsTable reloadData];
    }
	
	//update display according to current linea state
	[self connectionState:linea.connstate];
}


- (void)viewDidLoad
{
	linea=[Linea sharedDevice];
	[linea addDelegate:self];
    printers=[[NSMutableArray alloc] init];
    [super viewDidLoad];
}

@end
