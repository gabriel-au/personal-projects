//
//  UITitle.m
//  EasyBand
//
//  Created by Eduardo Carminati on 13/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "UITitle.h"


@implementation UITitle

+ (void) setTitle: (NSString *) title onView: (UIView *) view {
	
	UILabel *titleBar = [[[UILabel alloc] init] autorelease];
	titleBar.text = title;
	//titleBar.textColor = [UIColor whiteColor];
	titleBar.textAlignment = UITextAlignmentCenter;
	titleBar.backgroundColor = [UIColor orangeColor];
	CGFloat hei = [UITitle getHeight];
	titleBar.frame = CGRectMake(0, 0, 320,hei);
	[view addSubview: titleBar];	
}

+ (CGFloat) getHeight {
	return 25.0;
}

@end
