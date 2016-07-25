//
//  Tweet.h
//  EasyBand
//
//  Created by Eduardo Carminati on 10/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Tweet : NSObject {
	NSString *content;
	NSString *date;
	UIImage *image;
	NSString *link;
}

@property (nonatomic,retain) NSString *link;
@property (nonatomic,retain) NSString *content;
@property (nonatomic,retain) NSString *date;
@property (nonatomic,retain) UIImage *image;

@end
