//
//  EasyBandAppDelegate.h
//  EasyBand
//
//  Created by Eduardo Carminati on 08/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>
#import "PlayerViewController.h"
@interface EasyBandAppDelegate : NSObject <UIApplicationDelegate, UITabBarControllerDelegate> {
    UIWindow *window;
    UITabBarController *tabBarController;
	UIView *imgView;
	
	NSMutableArray *tweets;
	NSMutableArray *videos;
	NSMutableArray *photos;
	NSMutableArray *agendas;
	NSMutableArray *songs;
	
	IBOutlet PlayerViewController *playerController;
}

-(void)removeSplash;

@property (nonatomic, retain) IBOutlet UIWindow *window;

@property (nonatomic, retain) IBOutlet UITabBarController *tabBarController;
@property (nonatomic, retain) IBOutlet PlayerViewController *playerController;

@property (nonatomic, retain) NSMutableArray *tweets;
@property (nonatomic, retain) NSMutableArray *videos;
@property (nonatomic, retain) NSMutableArray *photos;
@property (nonatomic, retain) NSMutableArray *agendas;
@property (nonatomic, retain) NSMutableArray *songs;

@end
