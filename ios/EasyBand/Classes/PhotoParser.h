//
//  PhotoParser.h
//  EasyBand
//
//  Created by Eduardo Carminati on 15/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Photo.h"

@interface PhotoParser : NSObject  <NSXMLParserDelegate>  {
	Photo *photo;
}


@property (nonatomic,retain) Photo *photo;

@end
