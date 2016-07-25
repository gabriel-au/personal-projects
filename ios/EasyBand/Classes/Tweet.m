//
//  Tweet.m
//  EasyBand
//
//  Created by Eduardo Carminati on 10/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Tweet.h"


@implementation Tweet
@synthesize content,date,image,link;

- (void) dealloc {
	NSLog(@"DEALLOC NO TWEET");
	[super dealloc];
}
@end
