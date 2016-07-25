//
//  Network.h
//  Network
//
//  Created by Alexandre Oliveira on 5/12/11.
//  Copyright (c) 2011 BRQ IT Services. All rights reserved.
//

#import <Foundation/Foundation.h>


@protocol NetworkRequestDelegate <NSObject>

@required

- (void)executou: (NSData *) dados;
- (void)falhou: (NSError *) erro;

@end

@interface Network : NSObject {
    NSURLConnection* conexao;
    id <NetworkRequestDelegate> delegate;
    NSMutableData* dadosRecebidos;
    
}

@property (nonatomic,assign) id <NetworkRequestDelegate> delegate;

- (void) requestSincrono:(NSURL *)url;
- (void) requestAssincrono:(NSURL *)url;


@end
