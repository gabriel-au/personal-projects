#import <UIKit/UIKit.h>
#import "PrinterSDK.h"
#import "ProgressViewController.h"

@interface BluetoothViewController : UIViewController <PrinterDelegate,UITableViewDataSource,UITableViewDelegate>
{
    IBOutlet ProgressViewController *progressViewController;
    
    IBOutlet UITableView *printersTable;
    
    Printer *prn;
}

-(IBAction)onBTDiscover:(id)sender;
-(IBAction)onClose:(id)sender;

@property(copy) NSMutableArray *btAddresses;
@property(copy) NSMutableArray *btNames;
@end
