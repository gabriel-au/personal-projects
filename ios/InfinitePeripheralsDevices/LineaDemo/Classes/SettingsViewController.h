//
//  SettingsViewController.h
//
//  Created by Anton Rajnov on 8/30/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import "ScannerViewController.h"
#import "ProgressViewController.h"

@interface SettingsViewController : UIViewController <LineaDelegate,UITableViewDataSource,UITableViewDelegate,UIAlertViewDelegate> {
	IBOutlet UITableView *settingsTable;
	IBOutlet ProgressViewController *progressViewController;

	Linea *linea;
    
    int progressPhase;
    int progressPercent;
    
    int fwUpdateTarget;

    NSMutableArray *printers;
}

@property(nonatomic,copy) NSString *selectedPrinterName;
@property(nonatomic,copy) NSString *selectedPrinterAddress;
@property(nonatomic,copy) NSString *firmwareFile;

@property(assign) int scanMode;

@end
