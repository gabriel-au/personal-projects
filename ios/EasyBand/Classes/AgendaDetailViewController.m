//
//  AgendaDetailViewController.m
//  EasyBand
//
//  Created by Eduardo Carminati on 22/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "AgendaDetailViewController.h"
#import "Agenda.h"

@implementation AgendaDetailViewController
@synthesize agenda,webView,string,loading;
// The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
/*
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization.
    }
    return self;
}
*/

- (id) initWithAgenda: (Agenda *) agenda2 {
	if (self = [super init]) {
		agenda = agenda2;
	}
	return self;
}

// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
	
	webView = [[UIWebView alloc] initWithFrame:CGRectMake(0, 0, 320, 480-self.navigationController.navigationBar.frame.size.height - 49)];
	webView.delegate = self;
	
	string = [[NSMutableString alloc] init];
	
	[string appendString:@"<html><head><style>body{font-family:\"Arial\", Arial, serif;}.t{font-size: 25pt;}.s{font-size: 20pt;}.l{font-size: 13pt;}</style></head><body><p class=\"t\">@TITULO</p><p>@DIA/@MES/2011 - @HORA</p><p class=\"l\">@ENDERECO</p><center><img style=\"-webkit-user-select: none; cursor: -webkit-zoom-in; \" src=\"http://maps.google.com/maps/api/staticmap?center=@LAT,@LONG;zoom=14&amp;size=512x512&amp;maptype=roadmap&amp;markers=color:red|label:o|@LAT,@LONG&amp;sensor=false\" width=\"302\" height=\"302\"></center></body></html>"];
	
//	@property (nonatomic,retain) 	NSString *month;
//	@property (nonatomic,retain) 	NSString *day;
//	@property (nonatomic,retain) 	NSString *hour;
//	@property (nonatomic,retain) 	NSString *city;
//	@property (nonatomic,retain) 	NSString *address;
//	@property (nonatomic,retain) 	NSString *lat;
//	@property (nonatomic,retain) 	NSString *lng;
	
	[string replaceOccurrencesOfString: @"@TITULO" withString:agenda.city options: NSLiteralSearch range: NSMakeRange(0, [string length])];
	[string replaceOccurrencesOfString: @"@ENDERECO" withString:agenda.address options: NSLiteralSearch range: NSMakeRange(0, [string length])];
	[string replaceOccurrencesOfString: @"@HORA" withString:agenda.hour options: NSLiteralSearch range: NSMakeRange(0, [string length])];
	[string replaceOccurrencesOfString: @"@MES" withString:agenda.month options: NSLiteralSearch range: NSMakeRange(0, [string length])];
	[string replaceOccurrencesOfString: @"@DIA" withString:agenda.day options: NSLiteralSearch range: NSMakeRange(0, [string length])];
	[string replaceOccurrencesOfString: @"@LAT" withString:agenda.lat options: NSLiteralSearch range: NSMakeRange(0, [string length])];
	[string replaceOccurrencesOfString: @"@LONG" withString:agenda.lng options: NSLiteralSearch range: NSMakeRange(0, [string length])];
	
	NSLog(@"%@",string);
	
	[self.webView loadHTMLString: string baseURL:nil];
	
	[self.view addSubview: self.webView];
	
}


- (void)webViewDidStartLoad:(UIWebView *)webView{
	loading = [[UILoading alloc] initWithView: self.view];
}

- (void)webViewDidFinishLoad:(UIWebView *)webView{
	[loading stop];
}

/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations.
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (void)viewDidUnload {
    [super viewDidUnload];
}


- (void)dealloc {
	[string release];
	[webView release];
	[loading release];
	[agenda release];
    [super dealloc];
}


@end
