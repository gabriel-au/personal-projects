//
//  TwitterParser.h
//  EasyBand
//
//  Created by Eduardo Carminati on 10/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Tweet.h"

@interface TwitterParser : NSObject <NSXMLParserDelegate>  {
	NSMutableArray *tweets;
	Tweet *tweet;
	NSMutableString *name;
	BOOL record;
}

@property (nonatomic,retain)	NSMutableArray *tweets;
@property (nonatomic,retain)	Tweet *tweet;
@property (nonatomic,retain)	NSMutableString *name;
@property (nonatomic)			BOOL record;

@end
