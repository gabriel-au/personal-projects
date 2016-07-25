//
//  PlayerViewController.m
//  EasyBand
//
//  Created by Eduardo Carminati on 17/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "PlayerViewController.h"
#import "UITitle.h"

@implementation PlayerViewController
@synthesize songTitle,song,player,loading,item,time,songs,buttonPlay,buttonPause,volumeView, volumeSlider;

- (void) loadView {
	[super loadView];
	
	self.navigationItem.rightBarButtonItem = nil;

	[volumeView sizeToFit];
	[self.view addSubview:volumeView];
	
	for (UIView *view in [volumeView subviews]){
		if ([[[view class] description] isEqualToString:@"MPVolumeSlider"]) {
			volumeSlider = (UISlider *) view;
		}
	}
	
	[[NSNotificationCenter defaultCenter] addObserver:self 
											 selector:@selector(volumeChanged:) 
												 name:@"AVSystemController_SystemVolumeDidChangeNotification" 
											   object:nil];
	
	buttonPause.hidden = YES;
}

- (void) volumeChanged: (NSNotification *) notify {
	[volumeSlider setValue:[[[notify userInfo] objectForKey:@"AVSystemController_AudioVolumeNotificationParameter"] floatValue]];
}

- (void) setData: (Song *) s forSongs: (NSArray *) songList {
	self.song = s;
	self.songTitle.text = s.name;
	self.songs = songList;
}

- (IBAction) play: (id) sender {
	[self.loading startAnimating];
	self.loading.hidden = NO;
	
	if (self.player != nil) {
		[self.player pause];
		[self.player release];
	}
	if (self.song == NULL) {
		self.song = [self.songs objectAtIndex: 0];
		[self setData: self.song forSongs: self.songs];
	}
	
	NSString *urlString = [NSString stringWithFormat: @"http://www.bandasurfsessions.com.br/%@", song.url];
	NSURL *url = [NSURL URLWithString: urlString ];
	
	self.item = [AVPlayerItem playerItemWithURL: url];
	
	self.player = [[AVPlayer alloc] initWithPlayerItem: item];
	
	
	[[player addPeriodicTimeObserverForInterval:CMTimeMake(1, 3)
												   queue:NULL
											  usingBlock:^(CMTime time){
												  self.buttonPlay.hidden = YES;
												  buttonPause.hidden = NO;
												  CMTime currentTime = player.currentTime;
												  UInt64 currentTimeSec = currentTime.value / currentTime.timescale;
												  if (currentTimeSec > 0) {
													  [self.loading stopAnimating];
													  self.loading.hidden = YES;
												  }
												  UInt32 minutes = currentTimeSec / 60;
												  UInt32 seconds = currentTimeSec % 60;
												  NSString *tims = [NSString stringWithFormat:
																			@"%02d:%02d", minutes, seconds];
												  
												  self.time.text = tims;
					
											  }] retain];
	
	[self.player play];
	[[AVAudioSession sharedInstance] setCategory:AVAudioSessionCategoryPlayback error:nil];
}

- (IBAction) pause: (id) sender {
	[self performSelector: @selector(showPlayButton) withObject:nil afterDelay:0.0001];
	[self.player pause];
}

- (void) pausePlayer {
	[self.player pause];
}
- (void) showPlayButton {
	self.buttonPlay.hidden = NO;
	self.buttonPause.hidden = YES;
	self.loading.hidden = YES;
}

- (IBAction) next: (id) sender {
	int index = [self.songs indexOfObject: self.song] + 1;
	if (index < [self.songs count]) {
		self.song = [self.songs objectAtIndex: index];
	}	
	[self setData: self.song forSongs: self.songs];
	[self play: self];
}
- (IBAction) back: (id) sender {
	int index = [self.songs indexOfObject: self.song] -1;
	if (index < 0) {
		index = 0;
	}
	self.song = [self.songs objectAtIndex: index];
	[self setData: self.song forSongs: self.songs];
	[self play: self];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (void)viewDidUnload {
    [super viewDidUnload];
}


- (void)dealloc {
    [super dealloc];
}


@end
