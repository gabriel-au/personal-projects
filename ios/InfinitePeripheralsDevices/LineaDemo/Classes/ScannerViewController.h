#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>

#import "LineaSDK.h"
#import "PrinterSDK.h"

@interface ScannerViewController : UIViewController <LineaDelegate,UITableViewDataSource,UITableViewDelegate> {
    
    IBOutlet UITableView *scanTable;
    
	IBOutlet UIButton *scanButton;
	IBOutlet UITextView *displayText;
	IBOutlet UITextView *debugText;
	IBOutlet UIImageView *statusImage;
	IBOutlet UIImageView *battery;
	IBOutlet UIButton *printButton;
	IBOutlet UIViewController *progressViewController;
    IBOutlet UILabel *voltageLabel;
    IBOutlet UITextField *numBarcodesField;
	
	NSMutableString *status;
	NSMutableString *debug;
    
    NSMutableArray *tableData;

	Linea *linea;
    
}

-(void)debug:(NSString *)text;

-(IBAction)scanDown:(id)sender;
-(IBAction)scanUp:(id)sender;
-(IBAction)onPrint:(id)sender;

@property (nonatomic,copy) NSString *lastBarcode;
@property (nonatomic,copy) NSString *lastBarcodeType;
@property (nonatomic,copy) NSString *lastCardName;
@property (nonatomic,copy) NSString *lastCardNumber;
@property (nonatomic,copy) NSString *lastExpDate;
@property (nonatomic,copy) NSString *lastCardTrack1;
@property (nonatomic,copy) NSString *lastCardTrack2;
@property (nonatomic,copy) NSString *lastCardTrack3;
@property (nonatomic, retain) UITableView *scanTable;

@end

ScannerViewController *scannerViewController;

#define SHOWERR(func) func; if(error)[scannerViewController debug:error.localizedDescription];
#define ERRMSG(title) {UIAlertView *alert = [[UIAlertView alloc] initWithTitle:title message:error.localizedDescription delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil, nil]; [alert show];}

