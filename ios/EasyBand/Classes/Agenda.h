//
//  Agenda.h
//  EasyBand
//
//  Created by Eduardo Carminati on 21/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Agenda : NSObject {
	
	NSString *month;
	NSString *day;
	NSString *hour;

	NSString *city;
	NSString *address;
	NSString *lat;
	NSString *lng;
	
}

@property (nonatomic,retain) 	NSString *month;
@property (nonatomic,retain) 	NSString *day;
@property (nonatomic,retain) 	NSString *hour;

@property (nonatomic,retain) 	NSString *city;
@property (nonatomic,retain) 	NSString *address;
@property (nonatomic,retain) 	NSString *lat;
@property (nonatomic,retain) 	NSString *lng;



@end
