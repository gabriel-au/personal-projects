//
//  EasyStreamViewController.m
//  EasyStream
//
//  Created by Gabriel Silva on 11/03/11.
//  Copyright 2011 Prime Mobile Solutions. All rights reserved.
//

#import "LiveStream.h"

@implementation LiveStream

@synthesize buttonPlay,buttonPause,player,item,loading,volumeView,volumeSlider;

- (void) loadView {
	[super loadView];
	
	
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
    [self playPlayer];
    buttonPause.hidden = YES;
}

- (void) volumeChanged: (NSNotification *) notify {
	[volumeSlider setValue:[[[notify userInfo] objectForKey:@"AVSystemController_AudioVolumeNotificationParameter"] floatValue]];
}

- (IBAction)play:(id)sender {
	[self playPlayer];
}

- (IBAction)pause: (id) sender {
	[self performSelector: @selector(showPlayButton) withObject:nil afterDelay:0.0001];
	[self.player pause];
}

- (void)playPlayer {
    [self.loading startAnimating];
	self.loading.hidden = NO;
    
	if (self.player != nil) {
		[self.player pause];
		[self.player release];
	}
	
	//http://servistream.pe:7030/listen.pls
	//http://centova.dnslink.com.br/cast/tunein.php/sc_ele64/playlist.pls
	NSURL *url = [NSURL URLWithString:@"http://centova.dnslink.com.br/cast/tunein.php/sc_ele64/playlist.pls"];
	
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
                                     }] retain];
	
	[self.player play];
	[[AVAudioSession sharedInstance] setCategory:AVAudioSessionCategoryPlayback error:nil];
}

- (void)pausePlayer {
	[self.player pause];
}

- (void)showPlayButton {
	self.buttonPlay.hidden = NO;
	self.buttonPause.hidden = YES;
	self.loading.hidden = YES;
}

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
	//[webView release];
    [super dealloc];
}

@end
