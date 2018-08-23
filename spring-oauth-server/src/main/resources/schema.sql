if OBJECT_ID('dbo.oauth_client_details', 'U') is not null
begin
	drop table "oauth_client_details"
end;

create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);




if OBJECT_ID('dbo.oauth_client_token', 'U') is null
begin
create table oauth_client_token (
  token_id VARCHAR(255),
  token VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
)
end

if OBJECT_ID('dbo.oauth_access_token', 'U') is null
begin
create table oauth_access_token (
  token_id VARCHAR(255),
  token VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication VARBINARY,
  refresh_token VARCHAR(255)
)
end

if OBJECT_ID('dbo.oauth_refresh_token', 'U') is null
begin
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token VARBINARY,
  authentication VARBINARY
)
end

if OBJECT_ID('dbo.oauth_code', 'U') is null
begin
create table oauth_code (
  code VARCHAR(255), authentication VARBINARY
)
end

if OBJECT_ID('dbo.oauth_approvals', 'U') is null
begin
create table oauth_approvals (
	userId VARCHAR(255),
	clientId VARCHAR(255),
	scope VARCHAR(255),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt DATETIME2
)
end

if OBJECT_ID('dbo.ClientDetails', 'U') is null
begin
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
)
end