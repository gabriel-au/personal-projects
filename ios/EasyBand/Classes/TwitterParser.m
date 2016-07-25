//
//  TwitterParser.m
//  EasyBand
//
//  Created by Eduardo Carminati on 10/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "TwitterParser.h"

@implementation TwitterParser
@synthesize tweets,tweet,record,name;

- (id) init {
	if (self = [super init]) {
		NSURL *url = [[NSURL alloc] initWithString:@ "http://twitter.com/statuses/user_timeline.atom?id=surfsessionsdf" ];

//		NSURLRequest *request = [[NSURLRequest alloc] initWithURL:url];
//		NSURLResponse *resp = nil;
//		NSError *err = nil;
//		NSData *data = [NSURLConnection sendSynchronousRequest:request returningResponse: &resp error: &err];
//		NSData *xmlData = data;
		

		NSXMLParser *parser = [[NSXMLParser alloc] initWithContentsOfURL:url];
		[parser setDelegate: self];
		[parser parse];
		[parser release];
		[url release];
	}
	return self;
}


- (void)parserDidStartDocument:(NSXMLParser *)parser {
	self.tweets = [NSMutableArray new];
	self.name = nil;
}
- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qualifiedName attributes:(NSDictionary *)attributeDict {

	if ([elementName isEqualToString:@"entry"]) {
		UIImage *image = [tweet.image retain];
		if (tweet != nil)
			[self.tweet release];
		self.tweet = [Tweet new];
		self.tweet.image = image;
		[self.tweets addObject:self.tweet];
	}
	if ([elementName isEqualToString:@"content"]) {
		if (self.name != nil) {
			[self.name release];
		}
		self.name = [[NSMutableString alloc] init];
	}
	if ([elementName isEqualToString: @"link"]) {
		if ( [[attributeDict objectForKey: @"rel"] isEqualToString: @"image"] && self.tweet.image == nil) {
			NSString *surl = [attributeDict objectForKey: @"href"];
			NSURL *url = [NSURL URLWithString: surl];
			NSData *data = [NSData dataWithContentsOfURL:url];
			UIImage *img = [[UIImage alloc] initWithData:data];
			tweet.image = img;
		} 
		
		else if ([[attributeDict objectForKey:@"type"] isEqualToString: @"text/html"]) {
			tweet.link = [attributeDict objectForKey:@"href"];
		}
	}
}
- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName {
	if ([elementName isEqualToString:@"content"]) {
		self.tweet.content = self.name;
		self.name = nil;
	}
}

- (void)parserDidEndDocument:(NSXMLParser *)parser {
			NSLog(@"END PARSE"); 
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	delegate.tweets = self.tweets;
}

- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string {
	if (self.name != nil) {
		[self.name appendString: string];
	}
}

- (void) dealloc {
	[tweet release];
	[tweets release];
	[name release];
	[super dealloc];
}
@end

