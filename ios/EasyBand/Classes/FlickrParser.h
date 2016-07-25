//
//  FlickrParser.h
//  EasyBand
//
//  Created by Eduardo Carminati on 15/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FlickrParser : NSObject  <NSXMLParserDelegate> {
	NSMutableArray *photos;
	NSMutableString *name;
}

@property (nonatomic,retain) NSMutableArray *photos;
@property (nonatomic,retain) NSString *name;

@end
