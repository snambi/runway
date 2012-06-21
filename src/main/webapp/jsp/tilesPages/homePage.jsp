<!-- Main hero unit for a primary marketing message or call to action -->
<!-- 
<div class="hero-unit">
    <h2>Welcome to CafeCoders!</h2>
    <p>
    Find a place to code. 
    Announce your location to other coders. 
    Let other coders find you.
    Happy Coding!
    <br/>
    <a class="btn btn-primary btn-large"  href="/findcafe">Get started &raquo;</a></p>
</div>
 -->
<div class="hero-unit app-hero">
		<div class="container">
			<div class="row">
				<div class="span6">
				<div class="home-image">
					<!--  <img src="http://placehold.it/570X440" /> -->
					<img src="../images/philz.jpg" />
				</div>
					
				</div>
				<div class="span6">
				<h2>Welcome to CafeCoders!</h2>
    <p>
    Find a place to code. 
    Announce your location to other coders. 
    Let other coders find you.
    Happy Coding!
    <br/>
					<div class="form-hero">
					<!-- Start of form for announce -->
					<form class="form-horizontal" method="POST"
						commandName="announceBean" action="/announce">
						<fieldset>
							<div class="control-group">
								<label class="control-label" for="topic">Working on</label>
								<div class="controls">
									<textarea rows="3" class="input large"
										placeholder="#android #java"></textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="location">In</label>
								<input type="hidden"  id="cafe-id" name="cafeId" value="105"/>
								<div class="controls">
									<input type="text" class="input large" id="cafe-location"
										name="location"
										data-provide="typeahead" data-items="4"
										placeholder="Starbucks or Philz or PaloAlto" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="date">On</label>
								<div class="controls">
									<input type="text"  class="input-medium" id="form_date" value=""
										data-date-format="mm/dd/yy" name="date" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="time">@</label>
								<div class="controls">
									<!-- 
									<input type="text" style="width: 300px;"  class="input-mini" id="time" name="time" />
									 -->
									<select name="time" id="form-time" class="input-medium">
										<option value="8">8 am</option>
										<option value="9">9 am</option>
										<option value="10">10 am</option>
										<option value="11">11 am</option>
										<option value="12">12 am</option>
										<option value="13">1 pm</option>
										<option value="14">2 pm</option>
										<option value="15">3 pm</option>
										<option value="16">4 pm</option>
										<option value="17">5 pm</option>
										<option value="18">6 pm</option>
										<option value="19">7 pm</option>
										<option value="20">8 pm</option>
										<option value="21">9 pm</option>
										<option value="22">10 pm</option>
									</select>
									<p class="help-block"></p>
									
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="duration">For</label>
								<div class="controls">
									<!-- 
									<input type="text" class="input-mini" id="duration"
										name="duration" placeholder="2 hrs"/>
										 -->
									<select name="duration" id="duration" class="input-medium">
										<option value="60">1 hr</option>
										<option value="120">2 hr</option>
										<option value="180">3 hr</option>
										<option value="240">4 hr</option>
										<option value="300">5 hr</option>
									</select>
									<p class="help-block"></p>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<input type="submit" class="btn btn-primary" value="create meetup" />
									<!-- 
									<input type="button" class="btn" value="Cancel" onclick="closeDialog ();" />
									 -->
								</div>
							</div>
						</fieldset>
					</form>
					</div> <!--  End of form in hero -->
				</div>
			</div>
		</div>
	</div>
	
<script>
	$(function() {
		$('#form_date').datepicker();
	});
</script>
<script>
// https://gist.github.com/1848558
// remote typeahead for bootstrap

/*
$(document).ready(function(){
	$('#cafe-location').typeahead({
		
		source: function (typeahead, query) {
		      var return_list = []
		      $("li").each(function(i,v){
		        return_list.push($(v).html())
		      })
		      // here I'm just returning a list of strings
		      return return_list
		    },
		    
	});
});
*/

$(document).ready(function(){

    var autocomplete = $('#cafe-location').typeahead();
    autocomplete.on('keyup', function(ev){ 

            ev.stopPropagation();
            ev.preventDefault();

            //filter out up/down, tab, enter, and escape keys
            if( $.inArray(ev.keyCode,[40,38,9,13,27]) === -1 ){

                var self = $(this);
                
                //set typeahead source to empty
                self.data('typeahead').source = [];

                //active used so we aren't triggering duplicate keyup events
                if( !self.data('active') && self.val().length > 0){

                    self.data('active', true);

                    //Do data request. Insert your own API logic here.
                    var url = "/r/searchcafes?term=" + $(this).val();
                    $.getJSON( url , function(data) {

                        //set this to true when your callback executes
                        self.data('active',true);
                        
                        // save the list of cafes in 'self'
                        self['cafes'] = data;

                        //Filter out your own parameters. Populate them into an array, since this is what typeahead's source requires
                        var arr = [], i=data.length;
                        
                        while(i--){
                            arr[i] = data[i].name;
                        }

                        //set your results into the typehead's source 
                        self.data('typeahead').source = arr;

                        //trigger keyup on the typeahead to make it search
                        self.trigger('keyup');

                        //All done, set to false to prepare for the next remote query.
                        self.data('active', false);

                    });
                };
            }
        });
    
    autocomplete.on('select', function( ev ){
    	console.log(ev);
    });    
});

</script>

	
