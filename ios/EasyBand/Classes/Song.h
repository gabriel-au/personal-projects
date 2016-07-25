//
//  Song.h
//  EasyBand
//
//  Created by Eduardo Carminati on 17/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Song : NSObject {
	NSString *name;
	NSString *url;
}

@property (nonatomic,retain) NSString * name;
@property (nonatomic,retain) NSString *url;

@end
