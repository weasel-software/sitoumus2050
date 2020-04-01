// @flow

export default function(service: string, payload: Object) {
  // $FlowFixMe
  return new Promise((fulfilled, rejected) => {
    window.Liferay
      ? window.Liferay.Service(
          service,
          payload,
          success => {
            return fulfilled(success);
          },
          error => {
            return rejected(new Error(error));
          }
        )
      : new Error("No Liferay in window object");
  });
}
