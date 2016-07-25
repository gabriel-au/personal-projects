//
//  InvokedUrlCommand.h
//  InvokedUrlCommand
//
//  Created by Alexandre Oliveira on 5/12/11.
//  Copyright (c) 2011 BRQ IT Services. All rights reserved.
//
#import <Foundation/Foundation.h>


@interface InvokedUrlCommand : NSObject {
	NSString* className;
	NSString* methodName;
    NSString* callbackName;
    NSURL*	  url;
}

@property(copy) NSString* className;
@property(copy) NSString* methodName;
@property(copy) NSString* callbackName;
@property(copy) NSURL* url;

+ (InvokedUrlCommand*) newFromUrl:(NSURL*)url;

- (void) dealloc;

@end
