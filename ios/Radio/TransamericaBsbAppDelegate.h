//
//  TransamericaBsbAppDelegate.h
//  TransamericaBsb
//
//  Created by Gabriel Silva on 14/03/11.
//  Copyright 2011 Prime Mobile Solutions. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TransamericaBsbAppDelegate : NSObject <UIApplicationDelegate, UITabBarControllerDelegate> {

}

@property (nonatomic, retain) IBOutlet UIWindow *window;

@property (nonatomic, retain) IBOutlet UITabBarController *tabBarController;

@end
