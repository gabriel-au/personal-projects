//
//  Const.m
//  EasyBand
//
//  Created by Eduardo Carminati on 21/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Const.h"


static Const *sharedInstance = nil;

@implementation Const
@synthesize loading,logo,photoActiveTab,orient;

#pragma mark -
#pragma mark class instance methods

#pragma mark -
#pragma mark Singleton methods

+ (Const *) sharedInstance
{
    @synchronized(self)
    {
        if (sharedInstance == nil)
			sharedInstance = [[Const alloc] init];
    }
    return sharedInstance;
}

- (id) init {
	if (self = [super init]){ 
		int x,y,w,h;
		w=206;h=40;
		x=320/2 - w/2;
		y=45/2 - h/2;
		logo = [[[UIView alloc] initWithFrame:CGRectMake(x, y, w, h)] autorelease];
		
		[logo addSubview: [[UIImageView alloc] initWithImage: [UIImage imageNamed:@"sol.png"]]];
		UIImageView *escrito = [[UIImageView alloc] initWithImage: [UIImage imageNamed:@"surfsessions.png"]];
		[escrito setFrame: CGRectMake(40, 5, 166,34)];
		[logo addSubview: escrito];
		
	}
	return self;
}

+ (id)allocWithZone:(NSZone *)zone {
    @synchronized(self) {
        if (sharedInstance == nil) {
            sharedInstance = [super allocWithZone:zone];
            return sharedInstance;  // assignment and return on first allocation
        }
    }
    return nil; // on subsequent allocation attempts return nil
}

- (id)copyWithZone:(NSZone *)zone
{
    return self;
}

- (id)retain {
    return self;
}

- (unsigned)retainCount {
    return UINT_MAX;  // denotes an object that cannot be released
}

- (void)release {
    //do nothing
}

- (id)autorelease {
    return self;
}

@end