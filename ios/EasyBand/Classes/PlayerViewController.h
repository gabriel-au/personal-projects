//
//  PlayerViewController.h
//  EasyBand
//
//  Created by Eduardo Carminati on 17/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <AVFoundation/AVFoundation.h>
#import <UIKit/UIKit.h>
#import "Song.h"
#import "UINavViewController.h"
#import <MediaPlayer/MediaPlayer.h>

@interface PlayerViewController : UINavViewController {
	IBOutlet UILabel *songTitle;
	IBOutlet UILabel *time;
	IBOutlet UIActivityIndicatorView *loading;
	IBOutlet UIButton *buttonPlay;
	IBOutlet UIButton *buttonPause;
	
	
	IBOutlet MPVolumeView *volumeView;
	UISlider *volumeSlider;

	AVPlayer *player;
	AVPlayerItem *item;
	
	Song *song;
	NSArray *songs;

}

- (IBAction) play: (id) sender; 
- (IBAction) pause: (id) sender;
- (IBAction) next: (id) sender;
- (IBAction) back: (id) sender;

- (void) setData: (Song *) s forSongs: (NSArray *) songList;

@property (nonatomic, retain) UISlider *volumeSlider;
@property (nonatomic, retain) 	IBOutlet MPVolumeView *volumeView;
@property (nonatomic, retain) IBOutlet UIButton *buttonPlay;
@property (nonatomic, retain) IBOutlet UIButton *buttonPause;
@property (nonatomic, retain) AVPlayerItem *item;
@property (nonatomic ,retain) AVPlayer *player;
@property (nonatomic ,retain) IBOutlet UILabel *songTitle;
@property (nonatomic ,retain) IBOutlet UILabel *time;
@property (nonatomic ,retain) IBOutlet UIActivityIndicatorView *loading;
@property (nonatomic ,retain) Song *song;
@property (nonatomic, retain) NSArray *songs;

@end
