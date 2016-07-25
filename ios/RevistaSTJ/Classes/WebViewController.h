//
//  WebViewController.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 26/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "FileUtil.h"



@interface WebViewController : UIViewController {
	IBOutlet UIWebView *wv;
}
@property (nonatomic,retain) 	IBOutlet UIWebView *wv;
@end
