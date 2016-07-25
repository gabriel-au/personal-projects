//
//  SongParser.h
//  EasyBand
//
//  Created by Eduardo Carminati on 17/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface SongParser : NSObject <NSXMLParserDelegate> {
	
	NSMutableArray *songs;

}

@property (nonatomic,retain) NSMutableArray *songs;


@end
