//
//  UnimedViewController.h
//  Unimed
//
//  Created by Alexandre Da Silva Oliveira on 21/09/11.
//  Copyright 2011 BRQ IT Services. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Loader.h"
#import "MBProgressHUD.h"


@interface UnimedViewController : UIViewController <UIWebViewDelegate>

{
    MBProgressHUD* HUD;
}

@property(nonatomic,retain)IBOutlet UIWebView* webView;
@property(nonatomic,retain) NSURL* url;
@property(nonatomic,retain) Loader* loader;


@end
