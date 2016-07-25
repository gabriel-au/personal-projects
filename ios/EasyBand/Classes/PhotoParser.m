//
//  PhotoParser.m
//  EasyBand
//
//  Created by Eduardo Carminati on 15/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "PhotoParser.h"
#import "Photo.h"
#import "FlickrParser.h"

@implementation PhotoParser
@synthesize photo;



- (id) initWithPhoto: (Photo *) phot {
	if (self = [super init]) {
		NSString *urlString = 
		[NSString stringWithFormat:
		 @"http://api.flickr.com/services/rest/?method=flickr.photos.getSizes&api_key=%@&photo_id=%@&format=rest", 
		 @"aaa45d3d746bb3b4b471d2bc6e712e4c", phot.photoId];
		self.photo = phot;
		[phot retain];

		NSURL *url = [[NSURL alloc] initWithString: urlString ];
		
		NSXMLParser *parser = [[NSXMLParser alloc] initWithContentsOfURL:url];
		[parser setDelegate: self];
		[parser parse];
		[parser release];
		[url release];
	}
	return self;
}

- (void)parserDidStartDocument:(NSXMLParser *)parser {
}
- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qualifiedName attributes:(NSDictionary *)attributeDict {
	if ([elementName isEqualToString:@"size"]) {
		if ([[attributeDict objectForKey: @"label"] isEqualToString: @"Square"]) {
			int w = [[attributeDict objectForKey: @"width"] intValue];
			int h = [[attributeDict objectForKey: @"height"] intValue];
			self.photo.thumbSize = CGSizeMake(w,h);
		}
		if ([[attributeDict objectForKey: @"label"] isEqualToString: @"Medium"]) {
			int w = [[attributeDict objectForKey: @"width"] intValue];
			int h = [[attributeDict objectForKey: @"height"] intValue];
			self.photo.size = CGSizeMake(w,h);
		}
		
	}
	
}
- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName {

}

- (void)parserDidEndDocument:(NSXMLParser *)parser {
}

- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string {
	
}

- (void) dealloc {
	[photo release];
	[super dealloc];
}

@end
