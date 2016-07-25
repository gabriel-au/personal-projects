//
//  CryptoViewController.h
//
//  Created by Anton Rajnov on 8/31/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import "ScannerViewController.h"

@interface EMSRCryptoViewController : UIViewController <LineaDelegate,UITextFieldDelegate> {
    IBOutlet UIView *cryptoView;
    
	IBOutlet UITextField *newAES256KeyEncryptionKey;
	IBOutlet UITextField *newAES256KeyEncryptionKeyVersion;
	IBOutlet UITextField *oldAES256KeyEncryptionKey;
    
	IBOutlet UITextField *newAES256EncryptionKey;
	IBOutlet UITextField *newAES256EncryptionKeyVersion;
    
    IBOutlet UISegmentedControl *emsrAlgorithmControl;

	Linea *linea;
}

-(IBAction)setAES256KeyEncryptionKey:(id)sender;
-(IBAction)setAES256EncryptionKey:(id)sender;
-(IBAction)setDUKPTEncryptionKey:(id)sender;
-(IBAction)getEMSRInfo:(id)sender;
-(IBAction)emsrAlgorithmChanged:(id)sender;
@end
