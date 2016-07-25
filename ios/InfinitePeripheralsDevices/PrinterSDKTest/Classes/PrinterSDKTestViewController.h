#import <UIKit/UIKit.h>
#import "PrinterSDK.h"
#import "ProgressViewController.h"

@interface PrinterSDKTestViewController : UIViewController {
	Printer *prn;
	NSMutableString *debug;
    
    IBOutlet ProgressViewController *progressViewController;
	
	IBOutlet UIImageView *statusImage;
	IBOutlet UIImageView *battery;
	IBOutlet UITextView *debugText;
	IBOutlet UITextView *displayText;
    IBOutlet UILabel *paperStatusLabel;
}

- (IBAction)onSelfTest:(id)sender;
- (IBAction)onFontsDemo:(id)sender;
- (IBAction)onGraphicsDemo:(id)sender;
- (IBAction)onScanBarcode:(id)sender;
- (IBAction)onBarcodesDemo:(id)sender;
- (IBAction)onClearLog:(id)sender;
- (IBAction)onSetEncryptionKey:(id)sender;
- (IBAction)onLoadLogo:(id)sender;

@end

