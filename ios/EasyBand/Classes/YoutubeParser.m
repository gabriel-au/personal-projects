//
//  YoutubeParser.m
//  EasyBand
//
//  Created by Eduardo Carminati on 09/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "YoutubeParser.h"


@implementation YoutubeParser
@synthesize videos,video,name,currentElement,record;


- (id) init {
	if (self = [super init]) {
		NSURL *url = [[NSURL alloc] initWithString:@ "http://gdata.youtube.com/feeds/api/users/rafaelmrosa/uploads" ];
		NSXMLParser *parser = [[NSXMLParser alloc] initWithContentsOfURL: url];
		[parser setDelegate: self];
		[parser parse];
		[url release];
	}
	return self;
}

- (void)parserDidStartDocument:(NSXMLParser *)parser {
	self.videos = [NSMutableArray new];
}

- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qualifiedName attributes:(NSDictionary *)attributeDict {
	if ([elementName isEqualToString:@"entry"]) {
		if (self.video != nil) {
			[self.video release];
		}
		self.video = [Video new];
		[self.videos addObject: self.video];
	}
	if ([elementName isEqualToString:@"media:thumbnail"]) {
		if (self.video.preview == nil) {
			id path = [attributeDict objectForKey: @"url"];
			NSURL *url = [NSURL URLWithString:path];
			NSData *data = [NSData dataWithContentsOfURL:url];
			UIImage *img = [[UIImage alloc] initWithData:data];
			self.video.preview = img;
		}
	}
	if ([elementName isEqualToString:@"media:content"]) {
		id url = [attributeDict objectForKey: @"url"];
		if (self.video.url == nil) {
			self.video.url = url;
		}
	}
	if ([elementName isEqualToString:@"media:title"]) {
		if (self.name != nil) {
			[self.name release];
		}
		name = [[NSMutableString alloc] initWithString:@""];
		[video setName:name];
		record = YES;
	} else if (record == YES) {
		record = NO;
	}
	currentElement = elementName;
}
- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName {

}

- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string {
	if (record == YES)
		[name appendString: string];
}

- (void)parserDidEndDocument:(NSXMLParser *)parser {
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	delegate.videos = self.videos;
}


- (void) dealloc {
	[video release];
	[name release];
	[videos release];
	[super dealloc];
}
@end
