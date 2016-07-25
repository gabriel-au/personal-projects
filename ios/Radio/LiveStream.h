//
//  EasyStreamViewController.h
//  EasyStream
//
//  Created by Gabriel Silva on 11/03/11.
//  Copyright 2011 Prime Mobile Solutions. All rights reserved.
//

#import <AVFoundation/AVFoundation.h>
#import <UIKit/UIKit.h>
#import <MediaPlayer/MediaPlayer.h>

@interface LiveStream : UIViewController {
	IBOutlet UIButton *buttonPlay;
    IBOutlet UIButton *buttonPause;
	IBOutlet UIActivityIndicatorView *loading;
	
	IBOutlet MPVolumeView *volumeView;
	UISlider *volumeSlider;
	
	AVPlayer *player;
	AVPlayerItem *item;
	
}

- (IBAction)play:(id)sender;
- (IBAction)pause:(id)sender;

@property (nonatomic, retain) IBOutlet MPVolumeView *volumeView;
@property (nonatomic, retain) IBOutlet UIButton *buttonPlay;
@property (nonatomic, retain) IBOutlet UIButton *buttonPause;
@property (nonatomic ,retain) IBOutlet UIActivityIndicatorView *loading;
@property (nonatomic, retain) AVPlayerItem *item;
@property (nonatomic ,retain) AVPlayer *player;
@property (nonatomic, retain) UISlider *volumeSlider;

@end

