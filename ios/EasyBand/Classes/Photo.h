//
//  Photo.h
//  EasyBand
//
//  Created by Eduardo Carminati on 14/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Three20/Three20.h>

@interface Photo : NSObject <TTPhoto> {
	CGSize thumbSize;
	CGSize imageSize;
	NSString *photoId;
	id<TTPhotoSource> _photoSource;
	NSString* _thumbURL;
	NSString* _smallURL;
	NSString* _URL;
	CGSize _size;
	NSInteger _index;
	NSString* _caption;
}

- (id)initWithURL:(NSString*)URL smallURL:(NSString*)smallURL  caption:(NSString*)caption;

- (id) initWithFarm: (NSString *) farm andServer: (NSString *) server andId: (NSString *) ide andSecrect: (NSString*) secret;

- (id)initWithURL:(NSString*)URL smallURL:(NSString*)smallURL size:(CGSize)size;

- (id)initWithURL:(NSString*)URL smallURL:(NSString*)smallURL size:(CGSize)size
		  caption:(NSString*)caption;

@property (nonatomic,retain) NSString *photoId;
@property (nonatomic) CGSize thumbSize;
@property (nonatomic) CGSize imageSize;

@end
