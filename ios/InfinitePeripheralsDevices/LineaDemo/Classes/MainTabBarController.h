#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import "ScannerViewController.h"
#import "SettingsViewController.h"
#import "CryptoViewController.h"
#import "RFViewController.h"
#import "EMSRCryptoViewController.h"

@interface MainTabBarController : UITabBarController <LineaDelegate> {
	IBOutlet ScannerViewController *scannerViewController;
	IBOutlet SettingsViewController *settingsViewController;
	IBOutlet CryptoViewController *cryptoViewController;
	IBOutlet RFViewController *rfViewController;
	IBOutlet EMSRCryptoViewController *emsrCryptoViewController;
	
	Linea *linea;
}

@end
