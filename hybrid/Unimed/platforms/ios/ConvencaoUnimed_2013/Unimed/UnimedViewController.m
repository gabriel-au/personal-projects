//
//  UnimedViewController.m
//  Unimed
//
//  Created by Alexandre Da Silva Oliveira on 21/09/11.
//  Copyright 2011 BRQ IT Services. All rights reserved.
//

#import "UnimedViewController.h"
#import "FileSystem.h"
#import "Updater.h"

@implementation UnimedViewController

@synthesize webView,url,loader;

#pragma mark javascript request filter
- (BOOL)webView:(UIWebView *)webView2 shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType {
    
    CGRect frame = webView.frame;

    frame.size.height = ([[UIScreen mainScreen] bounds].size.height + 15);
    frame.size.width = [[UIScreen mainScreen] bounds].size.width;
    
    webView.frame = frame;
    
    /*if (IS_IPHONE5) {
        
        CGRect frame = webView.frame;
        
        frame.size.height = [[UIScreen mainScreen] bounds].size.height;
        frame.size.width = [[UIScreen mainScreen] bounds].size.width;
        
        webView.frame = frame;
        
        NSLog(@"iPhone5");
        
    }*/
    
    //NSLog (@"Tamanho: %f",viewSize.width);
	
	url = [request URL];
	
	if ([[url scheme] isEqualToString:@"brq"]) {
		
		InvokedUrlCommand* iuc = [[InvokedUrlCommand newFromUrl:url] autorelease];
        [loader execute:iuc usingView:webView2];
		return NO;
	}
	
	return YES;
}


- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle


// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad
{
    
    [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
    
    
    loader = [[Loader alloc] init];
    [webView setDelegate:self];
    [FileSystem copyDirectoryToDocumentsFolder:@"www" overwrite:YES] ;
    [self performSelector:@selector(configureView)];
    //
    [[[Loader alloc] initWithView:self.webView usingFile:@"index.html" inDirectory:@"www"] release];   
    
//    HUD = [[MBProgressHUD alloc] initWithView:self.view];
//    [self.view addSubview:HUD];

   // HUD.labelText = @"Aguarde";
   // [HUD showWhileExecuting:@selector(update) onTarget:self withObject:nil animated:YES];
}

- (void) configureView
{
    self.webView.dataDetectorTypes = UIDataDetectorTypeNone;
    
    for (id subview in webView.subviews) {
        if ([[subview class] isSubclassOfClass: [UIScrollView class]]){
            ((UIScrollView *)subview).bounces = NO;
        }
    }
}

- (void) update 
{
    Updater* updater = [[Updater alloc] init];
   BOOL updated = [updater update:[NSURL URLWithString:@"http://dl.dropbox.com/u/42583976/update.plist"]];
    [updater release];    
    
    if (updated) {
        [[[Loader alloc] initWithView:self.webView usingFile:@"index.html" inDirectory:@"www"] release];   
    }
    
}


- (void) cleanHUD 
{
    [HUD hide:YES];
    [HUD removeFromSuperview];
    [HUD release];
    HUD = nil;
}


- (void)viewDidUnload
{
    loader = nil;
    self.webView = nil;    
    [super viewDidUnload];

}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

- (void)dealloc {
    
    [loader release];
    [url release];
    webView.delegate = nil;
    [webView release];
    [super dealloc];
}

@end
