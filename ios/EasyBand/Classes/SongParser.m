//
//  SongParser.m
//  EasyBand
//
//  Created by Eduardo Carminati on 17/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "SongParser.h"
#import "Song.h";

@implementation SongParser
@synthesize songs;

- (id) init {
	if (self = [super init]) {
		NSURL *url = [[NSURL alloc] initWithString:@ "http://www.bandasurfsessions.com.br/musicas/playlist.xml" ];

		NSXMLParser *parser = [[NSXMLParser alloc] initWithContentsOfURL:url];
		[parser setDelegate: self];
		[parser parse];
		[parser release];
		[url release];
	}
	return self;
}

- (void)parserDidStartDocument:(NSXMLParser *)parser {
	self.songs = [NSMutableArray new];
}
- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qualifiedName attributes:(NSDictionary *)attributeDict {
	if ([elementName isEqualToString:@"song"]) {
		Song *s = [Song new];
		s.name = [attributeDict objectForKey: @"name"];
		s.url = [attributeDict objectForKey: @"file"];
		[songs addObject: s];
		[s release];
	}
	
}
- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName {
	
}

- (void)parserDidEndDocument:(NSXMLParser *)parser {
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	delegate.songs = self.songs; 
}

- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string {
	
}

- (void) dealloc {
	[songs release];
	[super dealloc];
}

@end
