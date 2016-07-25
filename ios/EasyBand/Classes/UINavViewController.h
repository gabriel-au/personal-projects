//
//  UINavViewController.h
//  EasyBand
//
//  Created by Eduardo Carminati on 08/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "NavControl.h"
#import "UITitle.h"

@interface UINavViewController : UIViewController {
	
	UINavigationController	*navControl;

}

@property (nonatomic,retain) UINavigationController *navControl;

@end
