//
//  YoutubeParser.h
//  EasyBand
//
//  Created by Eduardo Carminati on 09/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Video.h"

@interface YoutubeParser : NSObject <NSXMLParserDelegate> {
	NSMutableArray *videos;
	Video *video;
	NSMutableString *name;
	NSString *currentElement;
	BOOL record;
}

@property (nonatomic,retain) NSMutableArray *videos;
@property (nonatomic,retain) Video *video;
@property (nonatomic,retain)	NSMutableString *name;
@property (nonatomic,retain)	NSString *currentElement;
@property (nonatomic) BOOL record;
@end
