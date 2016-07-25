//
//  AgendaParser.m
//  EasyBand
//
//  Created by Eduardo Carminati on 21/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "AgendaParser.h"


@implementation AgendaParser
@synthesize agendas;

- (id) init {
	BOOL teste = YES;
	if (self = [super init]) {
		if (teste == NO) {
			NSString *teste = @"<?xml version=\"1.0\" encoding=\"UTF-8\"?><schedules><schedule city=\"Taguatinga\" address=\"Taguaparque\" date=\"31/12/2010 20:00\" lat=\"-15.804657\" lng=\"-47.914494\"/>		<schedule city=\"Brasília\" address=\"Parque da Cidade - Estacionamento 9\" date=\"20/01/2011 20:00\" lat=\"-15.804657\" lng=\"-47.914494\" />		</schedules>";
			NSData *data = [teste dataUsingEncoding: NSUTF8StringEncoding];
			NSXMLParser *parser = [[NSXMLParser alloc] initWithData: data];
			[parser setDelegate: self];
			[parser parse];
			[parser release];	
		} else {
			NSURL *url = [[NSURL alloc] initWithString:@ "http://www.bandasurfsessions.com.br/agenda_iphone.xml" ];
			NSXMLParser *parser = [[NSXMLParser alloc] initWithContentsOfURL:url];
			[parser setDelegate: self];
			[parser parse];
			[parser release];
			[url release];
		}
	}
	return self;
}

/*
- (id) init {
	if (self = [super init]) {
		// PROCESSO DE LOGIN
		NSURL *url = [NSURL URLWithString:@"https://www.google.com/accounts/ClientLogin"];
		
		NSMutableURLRequest *loginRequest = [NSMutableURLRequest requestWithURL:url];
		[loginRequest setHTTPMethod:@"POST"];
		[loginRequest addValue:@"Content-Type" forHTTPHeaderField:@"application/x-www-form-urlencoded"];
		[loginRequest addValue:@"application/x-www-form-urlencoded" forHTTPHeaderField:@"Content-Type"];
		
		NSString *requestBody = [[NSString alloc]
								 initWithFormat:@"Email=%@&Passwd=%@&service=cl&source=%@",
								 @"eduardocarminati@primems.com.br",@"gig1918g", [NSString stringWithFormat:@"%@%d", @"EasyBand-1"]];
		[loginRequest setHTTPBody:[requestBody dataUsingEncoding:NSASCIIStringEncoding]];
		
		NSHTTPURLResponse *response = NULL;
		NSData *responseData = [NSURLConnection sendSynchronousRequest:loginRequest returningResponse: &response error:nil];
		NSString *responseStr = [[NSString alloc] initWithData:responseData encoding: NSASCIIStringEncoding];
		
		NSLog(@"AUTH TOKEN RECUPERADO");
		
		// RECUPERA APENAS O 'AUTH TOKEN'
		NSRange range = [responseStr rangeOfString: @"Auth="]; 
		int beginAuth = range.location + 5; 
		int length = [responseStr length] - beginAuth - 1; 
		NSString *authToken = [[NSString alloc] initWithString: [responseStr substringWithRange: NSMakeRange(beginAuth, length)]]; 
		
		// FAZ A REQUISIÇÃO DOS CALENDÁRIOS
		//NSString *urlString = @"https://www.google.com/calendar/feeds/primems.com.br_7l74he3asq1b84to5t3pejmj84%40group.calendar.google.com/private/full";
		NSString *urlString = @"http://www.google.com/calendar/feeds/primems.com.br_7l74he3asq1b84to5t3pejmj84%40group.calendar.google.com/public/basic";
		
		url = [NSURL URLWithString:urlString];
		NSMutableURLRequest *xmlRequest = [NSMutableURLRequest requestWithURL:url];
		NSString *authHeader = [NSString stringWithFormat: @"GoogleLogin auth=%@", authToken];
		NSLog(@"AUTH HEADER:\n%@", authHeader);
		[xmlRequest setHTTPMethod:@"POST"];
		
		NSString *body = @"";
		[xmlRequest setHTTPBody:[body dataUsingEncoding:NSASCIIStringEncoding]];
		
		
		NSMutableDictionary *headers = [[NSMutableDictionary alloc] init]; 
		[headers setValue: @"application/atom+xml" forKey: 
		 @"Content-Type"]; 
		[headers setValue: authHeader forKey: @"Authorization"]; 
		[xmlRequest setAllHTTPHeaderFields: headers]; 

		
		NSHTTPURLResponse *xmlResponse = NULL;
		NSData *xmlResponseData = [NSURLConnection sendSynchronousRequest:xmlRequest returningResponse: &xmlResponse error:nil];
		NSString *xmlResponseDataString = [[NSString alloc] initWithData:xmlResponseData encoding: NSASCIIStringEncoding];
		NSLog(@"Size: %i / Resposta do google: %@", [xmlResponseData length], xmlResponseDataString);
		
		if (![xmlResponseDataString isEqualToString: @"Premature end of file."]) {
			NSXMLParser *parser = [[NSXMLParser alloc] initWithData: xmlResponseData];
			[parser setDelegate: self];
			//[parser parse];
		} else {

		}
		
	}
	return self;
}*/

- (void)parserDidStartDocument:(NSXMLParser *)parser {
	self.agendas = [NSMutableArray new];
}

- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qualifiedName attributes:(NSDictionary *)attributeDict {
	if ([elementName isEqualToString: @"schedule"]) {
		Agenda *a = [Agenda new];

		a.city = [attributeDict objectForKey: @"city"];
		a.address = [attributeDict objectForKey: @"address"];
		a.lat = [attributeDict objectForKey: @"lat"];
		a.lng = [attributeDict objectForKey: @"lng"];
		
		NSString *date = [attributeDict objectForKey: @"date"];
		NSArray *dateArray = [date componentsSeparatedByString: @"/"];
		NSArray *dateHourArray = [date componentsSeparatedByString: @" "];
		
		a.day = [dateArray objectAtIndex: 0];
		a.month = [dateArray objectAtIndex: 1];
		a.hour = [dateHourArray objectAtIndex: 1];
		
		[agendas addObject: a];
		
		[a release];
	}
	
}
- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName {


}

- (void)parserDidEndDocument:(NSXMLParser *)parser {
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	delegate.agendas = self.agendas;
}


- (void) dealloc {
	[agendas release];
	[super dealloc];
}
@end

