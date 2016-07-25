//
//  CommandController.m
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/12/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import "CommandController.h"

@implementation CommandController

@synthesize assProviderConnection,
			aNotListenningCommands,
			aIgnoreSelectorsForCommand,
			aDirectCommands,
			sLastCommand,
			maData,
			sDeviceToken,
			aConnectedSelectors,
			aDisconnectedSelectors,
			aReconnectSelectors,
			aDidReadSelectors,
			aErrorSelectors,
			idAppDelegate,
			idResponseClass;

- (id)initWithHost:(NSString *)sHost onPort:(UInt16)iPort {
	aNotListenningCommands = [[NSArray alloc] initWithObjects:@"li", @"removeacompanhamento", nil];
	aIgnoreSelectorsForCommand = [[NSArray alloc] initWithObjects:@"li", @"removeacompanhamento", nil];
	aDirectCommands = [[NSArray alloc] initWithObjects:@"listaclassesprocesso", nil];
	maData = [[NSMutableArray alloc] init];
	assProviderConnection = [[AsyncSocket alloc] initWithDelegate:self];
	[assProviderConnection connectToHost:sHost onPort:iPort withTimeout:(NSTimeInterval)10.0 error:nil];
	[super init];
	return self;
}

- (void)reconnectToHost:(NSString *)sHost onPort:(UInt16)iPort {
	bReconnection = YES;
	[assProviderConnection connectToHost:sHost onPort:iPort withTimeout:(NSTimeInterval)10.0 error:nil];
}

- (void)onSocket:(AsyncSocket *)sock didConnectToHost:(NSString *)host port:(UInt16)port {
	[idAppDelegate performSelector:@selector(habilitaAbas)];
	[idAppDelegate performSelector:@selector(clienteConectado)];
	if (bReconnection) {
		if (![aIgnoreSelectorsForCommand containsObject:[[sLastCommand componentsSeparatedByString:@"|"] objectAtIndex:0]]) {
			if (aReconnectSelectors != nil && [aReconnectSelectors count] > 0) {
				for (NSString *sSelector in aReconnectSelectors) {
					[idResponseClass performSelector:NSSelectorFromString(sSelector)];
				}
			}
		}
		bReconnection = NO;
	}
}

- (void)onSocket:(AsyncSocket *)sock willDisconnectWithError:(NSError *)err {
	[idAppDelegate performSelector:@selector(desabilitaAbas)];
	NSLog(@"Erro: (%d) %@", [err code], [err localizedDescription]);
	[[UIApplication sharedApplication] setNetworkActivityIndicatorVisible:NO];
	[idAppDelegate performSelector:@selector(clienteDesconectado)];
	if (![aIgnoreSelectorsForCommand containsObject:[[sLastCommand componentsSeparatedByString:@"|"] objectAtIndex:0]]) {
		if (aDisconnectedSelectors != nil && [aDisconnectedSelectors count] > 0) {
			for (NSString *sSelector in aDisconnectedSelectors) {
				[idResponseClass performSelector:NSSelectorFromString(sSelector)];
			}
		}
	}
}

- (void)onSocket:(AsyncSocket *)sock didWriteDataWithTag:(long)tag {
	[idAppDelegate performSelector:@selector(desabilitaAbas)];
	if (bListen) {
		[assProviderConnection readDataToData:[AsyncSocket CRLFData] withTimeout:10 tag:0];
	} else {
		[idAppDelegate performSelector:@selector(habilitaAbas)];
	}

}

- (void)onSocket:(AsyncSocket *)sock didReadData:(NSData*)data withTag:(long)tag {
	[[UIApplication sharedApplication] setNetworkActivityIndicatorVisible:NO];
	[idAppDelegate performSelector:@selector(habilitaAbas)];
	NSData *nsdResponse = [data subdataWithRange:NSMakeRange(0, [data length] - 2)];
	NSString *sDataRead = [[[NSString alloc] initWithData:nsdResponse encoding:NSUTF8StringEncoding] autorelease];
	NSLog(@"%@", sDataRead);
	if (bListen) {
		[self organizeData:sDataRead];
	}
}

- (void)organizeData:(NSString *)sData {
	[maData removeAllObjects];
	if (![sData isEqual:@""]) {
		for (NSString *sCurrentRecord in [sData componentsSeparatedByString:@"|"]) {
			[maData addObject:[sCurrentRecord componentsSeparatedByString:@"#"]];
		}
	}
	if (![aIgnoreSelectorsForCommand containsObject:[[sLastCommand componentsSeparatedByString:@"|"] objectAtIndex:0]]) {
		if (aDidReadSelectors != nil && [aDidReadSelectors count] > 0) {
			for (NSString *sSelector in aDidReadSelectors) {
				[idResponseClass performSelector:NSSelectorFromString(sSelector)];
			}
		}
	}
	NSLog(@"%@", maData);
}

- (NSMutableArray *)retrieveData {
	return maData;
}

- (void)sendCommand:(NSString *)sCommand {
	sLastCommand = [[NSString stringWithString:sCommand] retain];
	[self becameDeaf:sCommand];
	if (![aDirectCommands containsObject:[[sCommand componentsSeparatedByString:@"|"] objectAtIndex:0]]) {
		sCommand = [[NSString alloc] initWithFormat:@"token#%@|%@\r\n", sDeviceToken, sCommand];
	} else {
		sCommand = [sCommand stringByAppendingString:@"\r\n"];
	}
	NSLog(@"%@", sCommand);
	NSData *nsdCommand = [sCommand dataUsingEncoding:NSUTF8StringEncoding];
	[assProviderConnection writeData:nsdCommand withTimeout:5.0 tag:0];
}

- (void)becameDeaf:(NSString *)sCommand {
	NSArray *aCommand = [sCommand componentsSeparatedByString:@"|"];
	if ([aNotListenningCommands containsObject:[aCommand objectAtIndex:0]]) {
		bListen = NO;
	} else {
		bListen = YES;
	}
}

- (void)dealloc {
    [super dealloc];
}


@end
