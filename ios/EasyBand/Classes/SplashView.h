//
//  SplashView.h
//  EasyBand
//
//  Created by Eduardo Carminati on 09/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface SplashView : UIViewController {

	IBOutlet UIView *modelView;
}

@property (nonatomic,retain) IBOutlet UIView *modelView;
- (void) showSplash;
- (void) hideSplash;

@end
