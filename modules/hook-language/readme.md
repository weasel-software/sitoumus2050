# sitoumus2050

Localization files.

Note! can be also used to override Liferay default messages. 
Using prefixes (like sit.) is adviced so that you don't accidentally override Liferay messages.

## Install

blade deploy

watch changes
blade deploy -w 

## To get localizations:

- Use Liferay js api: 
    - Liferay.Language.get('sit.test.message');
    - Liferay.Language.get('sit.test.message2',['sad']);
    