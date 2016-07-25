//
//  InvokedUrlCommand.h
//  InvokedUrlCommand
//
//  Created by Alexandre Oliveira on 5/12/11.
//  Copyright (c) 2011 BRQ IT Services. All rights reserved.
//

#import "Updater.h"
#import "Reachability.h"

@interface Updater (Private)


- (BOOL) isConnected;
- (void) startNetworkIndicator;
- (void) stopNetworkIndicator;
- (void) log:(NSURLResponse *) response;
- (NSData *) syncronousRequest:(NSURL *)url;

@end

@implementation Updater

- (id)init
{
    self = [super init];
    if (self) {
        
    }
    
    return self;
}


- (NSData *) syncronousRequest:(NSURL *)url  {
 
    [self startNetworkIndicator];
    NSError* error = nil;
    NSURLResponse* response = nil;
    NSMutableURLRequest* request = [NSMutableURLRequest requestWithURL:url];    
    [request setValue:@"gzip" forHTTPHeaderField:@"Accept-Encoding"];
    NSData* data =  [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
    
    
    #if (TARGET_IPHONE_SIMULATOR)
        NSLog(@"URL: %@",[url description]);
        [self log:response];
    #endif
    
    [self stopNetworkIndicator];
    
    return data;
}

-(BOOL) update:(NSURL *)url {
        
    BOOL updated = NO;
    
    if ([self isConnected]) {
        
        [self startNetworkIndicator];
        
    
        NSString *errorDescription = nil;
        NSPropertyListFormat format;
        
        NSData* versionData = [self syncronousRequest:url];
        
        NSDictionary *updateList = [NSPropertyListSerialization propertyListFromData:versionData mutabilityOption:NSPropertyListImmutable format:&format errorDescription:&errorDescription];
        
        NSArray *documentsPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES)objectAtIndex:0];
        NSString* pathVersion = [NSString stringWithFormat:@"%@/www/%@",documentsPath,@"version.txt"];
        double currentVersion = [[NSString stringWithContentsOfFile:pathVersion encoding:NSUTF8StringEncoding error:nil] doubleValue];
        NSString* newVersion = nil;
        
        
        
        for (NSDictionary* content in updateList) {
            
            double version = [[content objectForKey:@"version"] doubleValue];
            
            if (version > currentVersion) {
                
                updated = YES;
                
                newVersion = [content objectForKey:@"version"];
                
                NSURL* url = [NSURL URLWithString:[content objectForKey:@"url"]];
                NSData* data = [self syncronousRequest:url];
                
                NSString* destinationPath = [content objectForKey:@"path"];
                
                NSString* destination = [NSString stringWithFormat:@"%@%@",documentsPath,destinationPath];
                [data writeToFile:destination atomically:YES]; 
                
            }
        }
        
        
        NSData* data = [newVersion dataUsingEncoding:NSUTF8StringEncoding];
        [data writeToFile:pathVersion atomically:YES];
        
        
        [self stopNetworkIndicator];
    }

    return updated;
    
}

#pragma mark Network indicator Methods
- (void) startNetworkIndicator {
    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
}

- (void) stopNetworkIndicator {
    [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
}


#pragma mark Reachability Methods
- (BOOL) isConnected {
    return ([[Reachability reachabilityForInternetConnection] currentReachabilityStatus] != NotReachable);
}



#pragma mark Log Methods
- (void) log:(NSURLResponse *) response {
    
    NSHTTPURLResponse* httpResponse;
    httpResponse = (NSHTTPURLResponse *) response;
    int statusCode = [httpResponse statusCode];
    
    NSLog(@"HTTP response Headers %@", [httpResponse allHeaderFields]);
    NSLog(@"HTTP status code %d", statusCode);
    NSLog(@"HTTP status code %@", [httpResponse description]);    
}

@end
