//
//  NavControlFactory.m
//  EasyBand
//
//  Created by Eduardo Carminati on 08/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "NavControlFactory.h"

@implementation NavControlFactory

+ (UINavigationController *) createNavControl: (UINavigationController *) navCtrl {

	if (navCtrl == nil) {
		navCtrl = [[[UINavigationController alloc] init] autorelease];
	}
	[navCtrl.navigationBar setAutoresizesSubviews:YES];
	[navCtrl.navigationBar setTintColor: [UIColor orangeColor]];
	//[navCtrl.navigationBar addSubview: [Const sharedInstance].logo];

	return navCtrl;
}

@end
