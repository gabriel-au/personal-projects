//
//  Photo.m
//  EasyBand
//
//  Created by Eduardo Carminati on 14/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//
#import "Photo.h"

@implementation Photo
@synthesize photoSource = _photoSource, size = _size, index = _index, caption = _caption;
@synthesize photoId,thumbSize,imageSize;

- (id) initWithFarm: (NSString *) farm andServer: (NSString *) server andId: (NSString *) ide andSecrect: (NSString*) secret {
	NSString *imgUrl = [NSString stringWithFormat:
						@"http://farm%@.static.flickr.com/%@/%@_%@_m.jpg", farm, server, ide, secret];
	NSString *thUrl = [NSString stringWithFormat:
					   @"http://farm%@.static.flickr.com/%@/%@_%@_s.jpg", farm, server, ide, secret];
	self.photoId = ide;
	return [self initWithURL:imgUrl smallURL:thUrl caption: nil];
}

- (id)initWithURL:(NSString*)URL smallURL:(NSString*)smallURL  caption:(NSString*)caption {
	if (self = [super init]) {
		_photoSource = nil;
		_URL = [URL copy];
		_smallURL = [smallURL copy];
		_thumbURL = [smallURL copy];
		//		_caption = ;
		_index = NSIntegerMax;
	}
	return self;
}


- (id)initWithURL:(NSString*)URL smallURL:(NSString*)smallURL size:(CGSize)size {
	return [self initWithURL:URL smallURL:smallURL size:size caption:nil];
}

- (id)initWithURL:(NSString*)URL smallURL:(NSString*)smallURL size:(CGSize)size
		  caption:(NSString*)caption {
	if (self = [super init]) {
		_photoSource = nil;
		_URL = [URL copy];
		_smallURL = [smallURL copy];
		_thumbURL = [smallURL copy];
		_size = size;
//		_caption = ;
		_index = NSIntegerMax;
	}
	return self;
}



- (void)dealloc {
	TT_RELEASE_SAFELY(_URL);
	TT_RELEASE_SAFELY(_smallURL);
	TT_RELEASE_SAFELY(_thumbURL);
	TT_RELEASE_SAFELY(_caption);
	[super dealloc];
}

- (NSString*)URLForVersion:(TTPhotoVersion)version {
	if (version == TTPhotoVersionLarge) {
		return _URL;
	} else if (version == TTPhotoVersionMedium) {
		return _URL;
	} else if (version == TTPhotoVersionSmall) {
		return _smallURL;
	} else if (version == TTPhotoVersionThumbnail) {
		return _thumbURL;
	} else {
		return nil;
	}
}

@end
