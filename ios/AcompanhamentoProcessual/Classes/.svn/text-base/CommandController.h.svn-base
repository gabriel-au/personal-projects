//
//  CommandController.h
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/12/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import "AsyncSocket.h"

@interface CommandController : NSObject {
	AsyncSocket		*assProviderConnection;
	NSArray			*aNotListenningCommands;
	NSArray			*aIgnoreSelectorsForCommand;
	NSArray			*aDirectCommands;
	NSString		*sLastCommand;
	NSMutableArray	*maData;
	NSString		*sDeviceToken;
	NSArray			*aConnectedSelectors;
	NSArray			*aDisconnectedSelectors;
	NSArray			*aReconnectSelectors;
	NSArray			*aDidReadSelectors;
	NSArray			*aErrorSelectors;
	id				idAppDelegate;
	id				idResponseClass;
	BOOL			bListen;
	BOOL			bReconnection;
}

@property (nonatomic, retain) AsyncSocket		*assProviderConnection;
@property (nonatomic, retain) NSArray			*aNotListenningCommands;
@property (nonatomic, retain) NSArray			*aIgnoreSelectorsForCommand;
@property (nonatomic, retain) NSArray			*aDirectCommands;
@property (nonatomic, retain) NSString			*sLastCommand;
@property (nonatomic, retain) NSMutableArray	*maData;
@property (nonatomic, retain) NSString			*sDeviceToken;
@property (nonatomic, retain) id				idAppDelegate;
@property (nonatomic, retain) id				idResponseClass;
@property (nonatomic, retain) NSArray			*aConnectedSelectors;
@property (nonatomic, retain) NSArray			*aDisconnectedSelectors;
@property (nonatomic, retain) NSArray			*aReconnectSelectors;
@property (nonatomic, retain) NSArray			*aDidReadSelectors;
@property (nonatomic, retain) NSArray			*aErrorSelectors;

- (id)initWithHost:(NSString *)sHost onPort:(UInt16)iPort;
- (void)reconnectToHost:(NSString *)sHost onPort:(UInt16)iPort;
- (void)becameDeaf:(NSString *)sCommand;
- (void)sendCommand:(NSString *)sCommand;
- (void)organizeData:(NSString *)sResponse;
- (NSMutableArray *)retrieveData;


@end
