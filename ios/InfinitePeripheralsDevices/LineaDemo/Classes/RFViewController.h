#import <Foundation/Foundation.h>
#import "LineaSDK.h"


@interface RFViewController : UIViewController <UITextFieldDelegate> {
	IBOutlet UITextView *logView;
    
	Linea *linea;
}

-(IBAction)clear:(id)sender;

@end
