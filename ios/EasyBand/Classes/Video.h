//
//  Video.h
//  EasyBand
//
//  Created by Eduardo Carminati on 09/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Video : NSObject {
	UIImage *preview;
	NSString *name;
	NSString *url;
}

@property (nonatomic,retain) UIImage *preview;
@property (nonatomic,retain) NSString *name;
@property (nonatomic,retain) NSString *url;

@end
