# Quasar Datasource g8 template [![Build Status](https://travis-ci.org/slamdata/quasar-datasource.g8.svg?branch=master)](https://travis-ci.org/slamdata/quasar-datasource.g8)

Giter8 Template for a Quasar Datasource

```bash
$ sbt new slamdata/quasar-datasource.g8
```

You will be prompted for a few properties, answer the questions and accept the default for `name`. Assuming a `datasource_name` of "niftydb" There will be a new project created in `./quasar-datasource-niftydb/`:

```bash
$ cd quasar-datasource-niftydb
$ git init
$ git add .
$ git commit -S -m 'Initial commit'
$ git remote add upstream git@github.com:slamdata/quasar-datasource-niftydb.git
# create repo on github (see below)
$ git push upstream master
```

Once this is done, the only remaining step is to [`travis encrypt`](https://docs.travis-ci.com/user/encryption-keys/#Usage) the following variables and add them to the `env.global` section of the `.travis.yml`:

- `ENCRYPTION_PASSWORD` Find this in 1Password
- `GITHUB_TOKEN` Create a new developer OAuth token for the **slamdata-bot** account (credentials in 1Password). This token should have **repo** permissions.
- `GITHUB_ACCESS_TOKEN` Identical to `GITHUB_TOKEN`
- `DISCORD_WEBHOOK_TOKENS` Go into Discord. Select the cog next to the **#travis** channel, then choose **Webhooks** and then **Edit**. This variable should be set to the value which *follows* the `https://discordapp.com/api/webhooks/` url prefix.

Assuming Travis runs on `travis-ci.com` you need to use `travis encrypt --com`. Note that this currently not only holds for the private repositories under `slamdata`, but also its public repositories. Also good to supply `-r` explicitly, so the template for these commands looks like `travis encrypt --com -r slamdata/<repos-name> <VAR_NAME>="<secret>"`

Once those secure sections have been created (preserve the labeling comments, please), run `git push upstream master` and the first Travis build should run!

## Repository Creation

[Click here](https://github.com/organizations/slamdata/repositories/new). Put in the appropriate name and description and go to town. Go into **Settings** and disable **Wiki** and **Issues**. Once this is done, go to **Collaborators and Teams**, enter your password, and add the **Engineering** team as an **Admin** on the repository (publication will fail without this step!).

## Labels and Publication

Publication will be automatically enabled for your repository. You are thus expected to use the `sdmerge` script and a pull request workflow. You will need to create the following four labels on your repository:

- **version: release**
- **version: breaking**
- **version: feature**
- **version: revision**

The **version: release** label is no longer necessary once you have passed version **1.0.0**.

Once your first successful Travis build has completed, you *must* use the `sdmerge` script and pull requests! This is not optional.
