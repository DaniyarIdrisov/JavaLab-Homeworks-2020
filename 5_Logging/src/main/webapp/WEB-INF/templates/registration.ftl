<#ftl encoding="UTF-8"/>
<#import "parts/base.ftl" as p/>
<@p.page>

    <title>Registration</title>

    <div class="main_container">
        <div class="jumbotron" style="width:40%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem;">Registration
                    <button type="submit" class="btn btn-link"><span> <a
                                    href="/login">You are already registered?</a></span></button>
                </h1>


                <form name="my-form" action="registration" method="post">

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="login">Login</label>
                            <input type="text" class="form-control" id="login" name="login" placeholder="Type here"
                                   required>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Type here" required>
                        </div>
                    </div>

                    <button id="signUpButton" class="btn btn-primary btn-block my-4" type="submit">Sign in</button>


                </form>


            </div>
        </div>
    </div>

</@p.page>