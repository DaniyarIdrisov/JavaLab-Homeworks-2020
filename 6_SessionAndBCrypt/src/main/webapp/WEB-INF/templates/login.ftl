<#ftl encoding="UTF-8"/>
<#import "parts/base.ftl" as p/>
<@p.page>
    <title>Login</title>

    <div class="main_container">
        <div class="jumbotron" style="width:40%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem;">Login
                    <button
                            type="submit" class="btn btn-link" href="/registration"><span><a href="/registration">Don't have an account yet?</a></span>
                    </button>
                </h1>

                <form name="my-form" action="login" method="post">

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="login">Login</label>
                            <input type="text" class="form-control" id="login" name="login"
                                   placeholder="Type here your login">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Type here your password">
                        </div>
                    </div>

                    <button id="signUpButton" class="btn btn-primary btn-block my-4" type="submit">Sign in</button>

                </form>


            </div>
        </div>
    </div>
</@p.page>