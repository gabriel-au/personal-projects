//
//  FlickrParser.m
//  EasyBand
//
//  Created by Eduardo Carminati on 15/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "FlickrParser.h"
#import "Photo.h"
#import "PhotoParser.h"

@implementation FlickrParser
@synthesize photos,name;
NSString *const FlickrAPIKey = @"aaa45d3d746bb3b4b471d2bc6e712e4c";

- (id) init {
	if (self = [super init]) {
		NSString *urlString = 
		[NSString stringWithFormat:
		 @"http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=%@&user_id=%@&format=rest", 
		 @"aaa45d3d746bb3b4b471d2bc6e712e4c", @"57077223@N04"];
		
		NSURL *url = [[NSURL alloc] initWithString: urlString];
		
		NSXMLParser *parser = [[NSXMLParser alloc] initWithContentsOfURL:url];
		[parser setDelegate: self];
		[parser parse];
		[parser release];
		[url release];
	}
	return self;
}

- (void)parserDidStartDocument:(NSXMLParser *)parser {
	self.photos = [NSMutableArray new];
}
- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qualifiedName attributes:(NSDictionary *)attributeDict {
	if ([elementName isEqualToString:@"photos"]) {
		
	}
	if ([elementName isEqualToString:@"photo"]) {
		Photo *p = [Photo new];
		[self.photos addObject: p];
		[p initWithFarm:
					[attributeDict objectForKey: @"farm"]
								andServer: [attributeDict objectForKey: @"server"]
								andId: [attributeDict objectForKey: @"id"]
								andSecrect: [attributeDict objectForKey: @"secret"]
		];
		
		PhotoParser *photoParser = [[PhotoParser alloc] initWithPhoto: p];
		[p release];
		[photoParser release];
	}

}
- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName {

}

- (void)parserDidEndDocument:(NSXMLParser *)parser {
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	delegate.photos = self.photos; 
}

- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string {

}

- (void) dealloc {

	[super dealloc];
}



@end
